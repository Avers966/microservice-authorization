package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import lombok.Data;

/**
 * CaptchaDto
 *
 * @author Mikhail Chechetkin
 */

@Data
public class CaptchaDto {
    private String secret;
    private String image;
}
