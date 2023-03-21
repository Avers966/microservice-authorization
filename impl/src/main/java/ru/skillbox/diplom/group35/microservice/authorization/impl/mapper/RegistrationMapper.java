package ru.skillbox.diplom.group35.microservice.authorization.impl.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Account;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.CaptchaService;

/**
 * RegistrationMapper
 *
 * @author Mikhail Chechetkin
 */

@Mapper
public interface RegistrationMapper {

    @Mapping(source = "password1", target = "password")
    Account mapToAccount(RegistrationDto registrationDto);

    @Mapping(source = "password", target = "password1")
    RegistrationDto mapToDto(Account account);
}
