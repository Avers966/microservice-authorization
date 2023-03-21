package ru.skillbox.diplom.group35.microservice.authorization.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;

/**
 * User
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {
    @Column(name = "first_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String lastName;

    @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @Pattern(regexp = "^[_A-Za-z0-9-+]+ (.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)* (.[A-Za-z]{2,})$")
    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String email;

    @Column(name = "created_on", nullable = false)
    private ZonedDateTime createdOn;

    @Column(name = "updated_on", nullable = false)
    private ZonedDateTime updatedOn;
}
