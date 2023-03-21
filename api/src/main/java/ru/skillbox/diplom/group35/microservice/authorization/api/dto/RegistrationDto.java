package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;

/**
 * RegistrationDto
 *
 * @author Mikhail Chechetkin
 */

@Data
@Schema(description = "Дто для запроса на регистрацию")
public class RegistrationDto extends BaseDto {

    @Schema(description = "Электронная почта нового пользователя")
    private String email;
    @Schema(description = "Пароль нового пользователя")
    private String password1;
    @Schema(description = "Дубль пароля для проверки")
    private String password2;
    @Schema(description = "Фамилия")
    private String firstName;
    @Schema(description = "Имя")
    private String lastName;
    @Schema(description = "Код с изображения капчи")
    private String captchaCode;
    @Schema(description = "Идентификатор капчи")
    private String captchaSecret;
}
