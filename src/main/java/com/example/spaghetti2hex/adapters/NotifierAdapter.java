package com.example.spaghetti2hex.adapters;

import com.example.spaghetti2hex.ports.NotifierPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotifierAdapter implements NotifierPort {

  private final JavaMailSender mailSender;

  public NotifierAdapter(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void notify(String email, String trackingNumber, double cost) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(email);
      helper.setSubject("Shipping completed");
      helper.setText("Your order has been shipped. Tracking number: " + trackingNumber + ". Shipping cost: " + cost + "€");

      mailSender.send(message);

    } catch (MessagingException e) {
      throw new RuntimeException("Errore nell'invio email", e);
    }
  }

}
