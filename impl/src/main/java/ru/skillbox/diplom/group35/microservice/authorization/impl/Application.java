package ru.skillbox.diplom.group35.microservice.authorization.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ru.skillbox.diplom.group35.library.core.annotation.EnableBaseRepository;
import ru.skillbox.diplom.group35.microservice.authorization.impl.service.CaptchaService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Application
 *
 * @author Mikhail Chechetkin
 */

@EnableBaseRepository
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {

//        CaptchaService captchaService = new CaptchaService();
//        try {
//            ImageIO.write
//                    (captchaService.captchaImagePost(captchaService.captchaGenerator()),
//                            "png", new File("C:/Users/Professional/Desktop/123/captcha.jpg"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        SpringApplication.run(Application.class, args);
    }
}
