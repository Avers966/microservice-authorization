package ru.skillbox.diplom.group35.microservice.authorization.domain.model;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

/**
 * PasswordResetToken
 *
 * @author Marat Safagareev
 */
@Entity
@Getter
@Setter
@Table(name = "reset_token")
@NoArgsConstructor
public class PasswordResetToken extends BaseEntity {

  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "expiration_time", nullable = false)
  private ZonedDateTime expirationTime;
}
