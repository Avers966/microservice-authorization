package ru.skillbox.diplom.group35.microservice.authorization.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import ru.skillbox.diplom.group35.library.core.annotation.EnableBaseRepository;
import ru.skillbox.diplom.group35.library.core.annotation.EnableOpenFeign;
import ru.skillbox.diplom.group35.library.core.annotation.EnableSecurity;
/**
 * Application
 *
 * @author Mikhail Chechetkin
 */

@EnableCaching
@EnableSecurity
@EnableOpenFeign
@EnableBaseRepository
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
