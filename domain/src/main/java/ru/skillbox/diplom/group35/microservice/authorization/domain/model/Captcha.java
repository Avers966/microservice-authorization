package ru.skillbox.diplom.group35.microservice.authorization.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;
import java.awt.image.BufferedImage;
import java.time.ZonedDateTime;

/**
 * Captcha
 *
 * @author Mikhail Chechetkin
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Captcha extends BaseEntity {
//    @Column(name = "code", columnDefinition = "VARCHAR(255)", nullable = false)
//    private String code;

    @Column(name = "secret", columnDefinition = "VARCHAR(255)", nullable = false)
    private String secret;

    @Transient
    @Column(name = "image")
    private BufferedImage image;

    @Column(name = "created_on", nullable = false)
    private ZonedDateTime createdOn;
}
