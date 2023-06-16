package ru.skillbox.diplom.group35.microservice.authorization.impl.service;

import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordResetTokenDto;

/**
 * EmailService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecoveryMailService {

  private static final String MESSAGE_SUBJECT = "Восстановление пароля";
  private static final String TEMPLATE = "email-template";
  private final JavaMailSender mailSender;
  private final SpringTemplateEngine springTemplateEngine;
  @Value("${spring.mail.username}")
  private String from;
  @Value("${app.recovery.mail.host}")
  private String host;

  public void sendRecoveryEmail(PasswordResetTokenDto resetTokenDto) {
    log.info("Creating mail message from: {}", resetTokenDto);
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper messageHelper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
      Context context = getEmailContext(resetTokenDto);
      String emailContent = springTemplateEngine.process(TEMPLATE, context);
      messageHelper.setFrom(from);
      messageHelper.setTo(resetTokenDto.getEmail());
      messageHelper.setSubject(MESSAGE_SUBJECT);
      messageHelper.setText(emailContent, true);
      sendEmailAsync(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  @Async
  protected void sendEmailAsync(MimeMessage message) {
    log.info("Sending mail message: {}", message);
    mailSender.send(message);
  }

  private Context getEmailContext(PasswordResetTokenDto resetTokenDto) {
    String recoveryUrl = "http://" + host + "/change-password/" + resetTokenDto.getId();
    Context context = new Context();
    context.setVariable("firstName", resetTokenDto.getFirstName());
    context.setVariable("recoveryUrl", recoveryUrl);
    context.setVariable("expiration", resetTokenDto.getExpiration());
    return context;
  }
}
