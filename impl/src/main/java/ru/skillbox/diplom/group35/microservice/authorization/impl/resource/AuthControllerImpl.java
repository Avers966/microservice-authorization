package ru.skillbox.diplom.group35.microservice.authorization.impl.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group35.library.core.annotation.EnableExceptionHandler;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateResponseDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.CaptchaDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.resource.AuthController;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.AuthenticationService;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.CaptchaService;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.RegistrationService;

/**
 * AuthorizationController
 *
 * @author Mikhail Chechetkin
 */
@Slf4j
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final RegistrationService registrationService;

    private final AuthenticationService authenticationService;

    private final CaptchaService captchaService;

    @Override
    public void register(@RequestBody RegistrationDto registrationDto) {
        log.info("Registration new account, Email:{}", registrationDto.getEmail());
        registrationService.create(registrationDto);
    }

    @Override
    public ResponseEntity<RegistrationDto> passwordRecovery() {
        return null;
    }

    @Override
    public ResponseEntity<RegistrationDto> newPassword(
            @RequestParam(name = "linkId", defaultValue = "") String linkId) {
        return null;
    }

    @Override
    public ResponseEntity<AuthenticateResponseDto> login(@RequestBody AuthenticateDto authenticateDto) {
        log.info("Get authentication by email, Email:{}", authenticateDto.getEmail());
        log.info("Get authentication by password, Password:{}", authenticateDto.getPassword());
        return ResponseEntity.ok(authenticationService.getAuthenticationResponse(authenticateDto));
    }

    @Override
    public ResponseEntity<CaptchaDto> captcha() {
        return ResponseEntity.ok(captchaService.getCaptcha());
    }
}
