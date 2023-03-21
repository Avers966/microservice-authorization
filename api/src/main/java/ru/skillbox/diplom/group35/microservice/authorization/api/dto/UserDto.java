package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import lombok.Data;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;

import java.util.UUID;

/**
 * UserDto
 *
 * @author Georgii Vinogradov
 */
@Data
public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
