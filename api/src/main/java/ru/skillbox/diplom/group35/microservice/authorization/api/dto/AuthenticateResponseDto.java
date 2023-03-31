package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import lombok.Data;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;

/**
 * AuthenticateResponseDto
 *
 * @author Mikhail Chechetkin
 */

@Data
public class AuthenticateResponseDto{
    private String accessToken;
    private String refreshToken;
}
