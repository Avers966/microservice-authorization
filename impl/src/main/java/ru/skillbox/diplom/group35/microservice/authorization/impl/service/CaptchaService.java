package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.CaptchaDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;
import ru.skillbox.diplom.group35.microservice.authorization.domain.model.Captcha;
import ru.skillbox.diplom.group35.microservice.authorization.impl.repository.CaptchaRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Random;

/**
 * CaptchaService
 *
 * @author Mikhail Chechetkin
 */

@Service
@RequiredArgsConstructor
public class CaptchaService {
    private static final int CAPTCHA_LENGTH = 3;
    private static final int LEFT_LIMIT = 48;
    private static final int RIGHT_LIMIT = 57;
    private static final int IMAGE_WIDTH = 150;
    private static final int IMAGE_HEIGHT = 75;
    private static final String IMAGE_FORMAT = "png";

    private final CaptchaRepository captchaRepository;

    private String generateCaptcha(){
        StringBuilder builder = new StringBuilder();

        String basicCaptcha = new Random().ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .limit(CAPTCHA_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        builder.append(basicCaptcha.charAt(1)).append(basicCaptcha);
        return builder.toString();
    }

    private Captcha createCaptcha (String captchaString) {
        Captcha captcha = new Captcha();
        BufferedImage captchaImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2D = captchaImage.createGraphics();
        Font font = new Font("TimesNewRoman", Font.PLAIN, 66);
        g2D.setFont(font);
        FontMetrics fontMetrics = g2D.getFontMetrics();

        g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
        g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_DEFAULT);
        g2D.setColor(Color.WHITE);
        g2D.drawString(captchaString, 0, fontMetrics.getAscent());
        g2D.setStroke(new BasicStroke(4));
        g2D.drawLine(IMAGE_WIDTH, IMAGE_HEIGHT / 2, 0, IMAGE_HEIGHT / 2);
        g2D.drawLine(IMAGE_WIDTH, 3 * IMAGE_HEIGHT / 4, 0, 3 * IMAGE_HEIGHT / 4);
        g2D.dispose();

        captcha.setSecret(captchaString);
        captcha.setImage(captchaImage);
        captcha.setIsDeleted(false);
        return captcha;
    }

    public CaptchaDto getCaptcha() {
        Captcha captcha = createCaptcha(generateCaptcha());
        captcha.setCreatedOn(ZonedDateTime.now());
        captchaRepository.save(captcha);

        CaptchaDto captchaDto = new CaptchaDto();
        captchaDto.setSecret(captcha.getSecret());

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(captcha.getImage(), IMAGE_FORMAT, os);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String prefix = "data:image/" + IMAGE_FORMAT + ";base64, ";
        captchaDto.setImage(prefix + Base64.getEncoder().encodeToString(os.toByteArray()));

        return captchaDto;
    }

    public boolean captchaValidation (RegistrationDto dto){
        Captcha captchaOrigin = captchaRepository.getBySecret(dto.getCaptchaSecret());

        ZonedDateTime createdOn = captchaOrigin.getCreatedOn();
        if (createdOn.plus(5, ChronoUnit.MINUTES).isBefore(ZonedDateTime.now())) {
            return false;
        }

        return captchaOrigin.getSecret().equals(dto.getCaptchaCode());
    }
}
