package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordResetTokenDto;

/**
 * EmailService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecoveryMailService {

  private static final String RECOVERY_SUBJECT = "Восстановление пароля";
  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String from;
  @Value("${app.recovery.mail.host}")
  private String host;

  public void sendRecoveryEmail(PasswordResetTokenDto resetTokenDto) {
    log.info("Creating mail message from: {}", resetTokenDto);
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(from);
    mailMessage.setTo(resetTokenDto.getEmail());
    mailMessage.setSubject(RECOVERY_SUBJECT);
    mailMessage.setText(provideText(
        resetTokenDto.getFirstName(),
        resetTokenDto.getId().toString(),
        resetTokenDto.getExpiration()));
    sendMailAsync(mailMessage);
  }

  @Async
  protected void sendMailAsync(SimpleMailMessage mailMessage) {
    log.info("Sending mail message: {}", mailMessage);
    mailSender.send(mailMessage);
  }

  private String provideText(String firstName, String linkId, int expiration) {
    String s = System.lineSeparator();
    return "Здравствуйте, " + firstName + "!"
        + s + s + "Чтобы придумать новый пароль, перейдите по этой ссылке:"
        + s + "http://" + host + "/change-password/" + linkId
        + s + "Ссылка действительна в течении " + expiration + " минут.";
  }
}
