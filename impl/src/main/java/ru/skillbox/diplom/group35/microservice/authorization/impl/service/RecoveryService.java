package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.diplom.group35.library.core.exception.UnauthorizedException;
import ru.skillbox.diplom.group35.library.core.security.config.TechnicalUserConfig;
import ru.skillbox.diplom.group35.microservice.account.api.client.AccountFeignClient;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountSecureDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.NewPasswordDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordRecoveryDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordResetTokenDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.PasswordResetToken;
import ru.skillbox.diplom.group35.microservice.authorization.impl.mapper.RecoveryMapper;
import ru.skillbox.diplom.group35.microservice.authorization.impl.repository.PasswordTokenRepository;

/**
 * PasswordRecoveryService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecoveryService {

  private final TechnicalUserConfig technicalUserConfig;
  private final AccountFeignClient accountFeignClient;
  private final RecoveryMailService mailService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final PasswordTokenRepository passwordTokenRepository;
  private final RecoveryMapper recoveryMapper;
  @Value("${app.recovery.expiration-time}")
  private int expiration;

  public void getPasswordRecoveryMail(PasswordRecoveryDto recoveryDto) {
    PasswordResetTokenDto resetTokenDto = createResetToken(recoveryDto);
    log.info("Created recoveryToken: {}", resetTokenDto);
    mailService.sendRecoveryEmail(resetTokenDto);
  }

  public void setNewPassword(String linkId, NewPasswordDto newPasswordDto) {
    log.info("Attempt to set new password from linkId: {}", linkId);
    PasswordResetToken resetToken = passwordTokenRepository.getById(UUID.fromString(linkId));
    if (resetToken.getExpirationTime().isAfter(ZonedDateTime.now())) {
      AccountDto accountDto = new AccountDto();
      accountDto.setEmail(resetToken.getEmail());
      accountDto.setPassword(bCryptPasswordEncoder.encode(newPasswordDto.getPassword()));
      technicalUserConfig.executeByTechnicalUser(
          () -> accountFeignClient.updateByEmail(accountDto));
      log.info("Password change successful from linkId: {}", linkId);
    } else {
      String errorMessage = "Reset password link is expired!";
      throw new UnauthorizedException(errorMessage);
    }
  }

  private PasswordResetTokenDto createResetToken(PasswordRecoveryDto recoveryDto) {
    log.info("Creating recovery token for: {}", recoveryDto);
    String email = recoveryDto.getEmail();
    AccountSecureDto accountSecureDto = technicalUserConfig.executeByTechnicalUser(
        () -> accountFeignClient.getByEmail(email)).getBody();
    assert accountSecureDto != null;
    log.info("Found user {} by provided email", accountSecureDto.getFirstName());
    PasswordResetToken resetToken = new PasswordResetToken();
    resetToken.setIsDeleted(false);
    resetToken.setEmail(email);
    resetToken.setExpirationTime(ZonedDateTime.now().plusMinutes(expiration));
    PasswordResetTokenDto resetTokenDto = recoveryMapper.toResetDto(
        passwordTokenRepository.save(resetToken));
    resetTokenDto.setFirstName(accountSecureDto.getFirstName());
    resetTokenDto.setExpiration(expiration);
    return resetTokenDto;
  }
}
