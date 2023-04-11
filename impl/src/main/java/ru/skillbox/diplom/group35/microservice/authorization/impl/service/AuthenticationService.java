package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.annotation.JwtProvider;
import ru.skillbox.diplom.group35.library.core.exception.UnauthorizedException;
import ru.skillbox.diplom.group35.library.core.security.config.SecurityConfig;
import ru.skillbox.diplom.group35.library.core.security.jwt.JwtTokenProvider;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateResponseDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Account;
import ru.skillbox.diplom.group35.microservice.authorization.impl.repository.AccountRepository;

@Service
@JwtProvider
@RequiredArgsConstructor
public class AuthenticationService {


    private final JwtTokenProvider jwtTokenProvider;

    private final AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthenticateResponseDto getAuthenticationResponse(AuthenticateDto authenticateDto) throws UnauthorizedException {
        AuthenticateResponseDto authenticateResponseDto = new AuthenticateResponseDto();
        Account accountFromDb = accountRepository.findByEmail(authenticateDto.getEmail());
        if (accountFromDb != null
                && bCryptPasswordEncoder.matches(authenticateDto.getPassword(), accountFromDb.getPassword())) {
            String jwtToken = jwtTokenProvider.createToken(accountFromDb.getId(), accountFromDb.getEmail());
            authenticateResponseDto.setAccessToken(jwtToken);
            authenticateResponseDto.setRefreshToken(jwtToken);
        }
        else {
            throw new UnauthorizedException("Incorrect email or password");
        }

        return authenticateResponseDto;
    }
}
