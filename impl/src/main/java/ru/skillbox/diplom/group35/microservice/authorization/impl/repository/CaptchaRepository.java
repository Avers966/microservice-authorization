package ru.skillbox.diplom.group35.microservice.authorization.impl.repository;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Captcha;

/**
 * CaptchaRepository
 *
 * @author Mikhail Chechetkin
 */

@Repository
public interface CaptchaRepository extends BaseRepository<Captcha> {
    Captcha getBySecret(String secret);
}
