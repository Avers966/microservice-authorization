package ru.skillbox.diplom.group35.microservice.authorization.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import ru.skillbox.diplom.group35.library.core.annotation.EnableBaseRepository;
import ru.skillbox.diplom.group35.library.core.annotation.EnableSecurity;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.CaptchaService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Application
 *
 * @author Mikhail Chechetkin
 */

@EnableCaching
@EnableSecurity
@EnableBaseRepository
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
