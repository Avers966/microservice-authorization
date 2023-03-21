package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;

/**
 * CaptchaService
 *
 * @author Mikhail Chechetkin
 */

@Service
public class CaptchaService {
    public boolean captchaValidation (RegistrationDto dto){
        return true;
    }
}
