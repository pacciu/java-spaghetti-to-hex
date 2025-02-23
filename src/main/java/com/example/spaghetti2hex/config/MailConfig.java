package com.example.spaghetti2hex.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com"); // Cambia se usi un altro provider
    mailSender.setPort(587);
    mailSender.setUsername("tuo.email@gmail.com");
    mailSender.setPassword("tua-password"); // ⚠️ Usa variabili d'ambiente!

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true"); // Per il logging (opzionale)

    return mailSender;
  }
}