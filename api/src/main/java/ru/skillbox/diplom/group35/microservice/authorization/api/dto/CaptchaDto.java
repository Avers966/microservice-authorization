package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * CaptchaDto
 *
 * @author Mikhail Chechetkin
 */

@Data
@Schema(description = "Дто для запроса капчи")
public class CaptchaDto {
    @Schema(description = "Идентификатор капчи")
    private String secret;
    @Schema(description = "Изображение капчи закодированное в Base64")
    private String image;
}
