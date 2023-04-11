package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import lombok.Data;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.StatusCode;

import java.time.ZonedDateTime;

/**
 * AccountDto
 *
 * @author Denis_Kholmogorov
 */

@Data
public class AccountDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String photo;
    private String about;
    private String city;
    private String country;
    private StatusCode statusCode;
    private ZonedDateTime regDate;
    private ZonedDateTime birthDate;
    private String messagePermission;
    private ZonedDateTime lastOnlineTime;
    private Boolean isOnline;
    private Boolean isBlocked;
    private String photoId;
    private ZonedDateTime createdOn;
    private ZonedDateTime updatedOn;
}
