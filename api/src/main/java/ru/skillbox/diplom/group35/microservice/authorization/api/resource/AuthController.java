package ru.skillbox.diplom.group35.microservice.authorization.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateResponseDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.CaptchaDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.NewPasswordDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordRecoveryDto;
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
    ResponseEntity<RegistrationDto> getPasswordRecoveryMail(@RequestBody PasswordRecoveryDto passwordRecoveryDto);

    @PostMapping("/password/recovery/{linkId}")
    ResponseEntity<RegistrationDto> setNewPassword(@PathVariable String linkId,
        @RequestBody NewPasswordDto newPasswordDto);

    @PostMapping("/login")
    ResponseEntity<AuthenticateResponseDto> login(AuthenticateDto authenticateDto);

    @GetMapping("/captcha")
    ResponseEntity<CaptchaDto> captcha();

    @PostMapping("/refresh")
    ResponseEntity<AuthenticateResponseDto> refresh(AuthenticateResponseDto refreshToken);
}
