package ru.skillbox.diplom.group35.microservice.authorization.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateResponseDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.CaptchaDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;

/**
 * AuthController
 *
 * @author Mikhail Chechetkin
 */

@RequestMapping("/api/v1/auth")
public interface AuthController {

    @PostMapping("/register")
    void register(RegistrationDto registrationDto);

    @PostMapping("/password/recovery")
    ResponseEntity<RegistrationDto> passwordRecovery();

    @PostMapping("/password/recovery/{linkId}")
    ResponseEntity<RegistrationDto> newPassword(String linkId);

    @PostMapping("/login")
    ResponseEntity<AuthenticateResponseDto> login(AuthenticateDto authenticateDto);

    @GetMapping("/captcha")
    ResponseEntity<CaptchaDto> captcha();
}
