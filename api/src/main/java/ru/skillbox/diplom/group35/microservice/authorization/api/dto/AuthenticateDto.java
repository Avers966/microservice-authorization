package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import lombok.Data;

/**
 * AuthenticateDto
 *
 * @author Mikhail Chechetkin
 */

@Data
public class AuthenticateDto {
    private String email;
    private String password;
}
