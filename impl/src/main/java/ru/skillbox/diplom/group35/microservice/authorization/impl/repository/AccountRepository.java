package ru.skillbox.diplom.group35.microservice.authorization.impl.repository;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Account;

import java.util.List;

/**
 * AccountController
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface AccountRepository extends BaseRepository<Account> {
    boolean existsByEmail(String email);
    Account findByEmail(String email);
}
