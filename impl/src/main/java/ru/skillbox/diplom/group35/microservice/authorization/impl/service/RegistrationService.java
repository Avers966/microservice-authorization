package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Account;
import ru.skillbox.diplom.group35.microservice.authorization.impl.mapper.RegistrationMapper;
import ru.skillbox.diplom.group35.microservice.authorization.impl.repository.AccountRepository;

import java.time.ZonedDateTime;

/**
 * RegistrationService
 *
 * @author Mikhail Chechetkin
 */

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AccountRepository accountRepository;

    private final RegistrationMapper registrationMapper;

    @Autowired
    private CaptchaService captchaService;
    @Bean
    public BCryptPasswordEncoder encoder() { //TODO убери бин в конфигурацию
        return new BCryptPasswordEncoder();
    }

//    public ResponseEntity<RegistrationDto> create(RegistrationDto dto) {
    public void create(RegistrationDto dto) {
        Account account = registrationMapper.mapToAccount(dto);
        account.setIsDeleted(false);
        account.setCreatedOn(ZonedDateTime.now());
        account.setUpdatedOn(ZonedDateTime.now());

        if(dto.getPassword1().equals(dto.getPassword2())
        && captchaService.captchaValidation(dto)) {
            account.setPassword(encoder().encode(account.getPassword()));
            accountRepository.save(account);
        }
    }
}
