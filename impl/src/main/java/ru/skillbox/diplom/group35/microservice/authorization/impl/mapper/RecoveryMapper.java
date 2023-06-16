package ru.skillbox.diplom.group35.microservice.authorization.impl.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordResetTokenDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.PasswordResetToken;

/**
 * RecoveryMapper
 *
 * @author Marat Safagareev
 */
@Mapper(componentModel = "spring")
public interface RecoveryMapper {

  PasswordResetTokenDto toResetDto(PasswordResetToken passwordResetToken);
}