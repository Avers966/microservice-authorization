package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;

/**
 * PasswordResetTokenDto
 *
 * @author Marat Safagareev
 */
@Data
public class PasswordResetTokenDto {
  UUID id;
  String email;
  String firstName;
  int expiration;
  ZonedDateTime expirationTime;
}
