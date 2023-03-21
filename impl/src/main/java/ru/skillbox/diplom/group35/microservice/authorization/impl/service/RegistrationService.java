package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Account;
import ru.skillbox.diplom.group35.microservice.authorization.impl.repository.AccountRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegistrationService
 *
 * @author Mikhail Chechetkin
 */

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AccountRepository accountRepository;

    private Account dtoToAccount(RegistrationDto dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setIsDeleted(dto.getIsDeleted());
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword1());
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        return account;
    }

//    private boolean emailIsValid(String email) {
//        String emailPattern = "^[_A-Za-z0-9-+]+ (.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)* (.[A-Za-z]{2,})$";
//        Pattern pattern = Pattern.compile(emailPattern);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }

    private boolean accountExists(Account account) {
        String email = account.getEmail();
        List<Account> accounts = accountRepository.findAllByEmail(email);

        for (Account acc : accounts) {
            if (acc.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<RegistrationDto> create(RegistrationDto dto) {
        Account account = dtoToAccount(dto);

        if (
//                !emailIsValid(account.getEmail()) ||
                !(dto.getPassword1().equals(dto.getPassword2()))
                || accountExists(account)) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            accountRepository.save(account);
            return ResponseEntity.ok(dto);
        }
    }
}
