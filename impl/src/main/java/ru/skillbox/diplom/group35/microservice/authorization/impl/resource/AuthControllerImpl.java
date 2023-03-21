package ru.skillbox.diplom.group35.microservice.authorization.impl.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.resource.AuthController;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.CaptchaService;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.RegistrationService;

/**
 * AuthorizationController
 *
 * @author Mikhail Chechetkin
 */

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final RegistrationService registrationService;
    @Override
    public ResponseEntity<RegistrationDto> register() {
        RegistrationDto registrationDto = new RegistrationDto();

        if (!(new CaptchaService().captchaValidation(registrationDto))) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return registrationService.create(registrationDto);
        }
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
    public ResponseEntity<RegistrationDto> login() {
        return null;
    }

    @Override
    public ResponseEntity<RegistrationDto> logout() {
        return null;
    }

    @Override
    public ResponseEntity<RegistrationDto> captcha() {
        return null;
    }
}
