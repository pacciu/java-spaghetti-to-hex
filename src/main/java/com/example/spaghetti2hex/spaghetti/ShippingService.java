package com.example.spaghetti2hex.spaghetti;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ShippingService {

  private static final Logger LOG = LoggerFactory.getLogger(ShippingService.class);

  private final WebClient webClient;
  private final JavaMailSender mailSender;

  public ShippingService(WebClient webClient, JavaMailSender mailSender) {
    this.webClient = webClient;
    this.mailSender = mailSender;
  }

  public void processShipment(String orderId, String customerEmail, String address, double weight) {
    try {
      double shippingCost = calculateShippingCosts(address, weight);
      String trackingNumber = callCourierApi(orderId, address);
      notify(customerEmail, trackingNumber, shippingCost);
    } catch (Exception e) {
      LOG.error("While shipping: " + e.getMessage(), e);
    }
  }

  private double calculateShippingCosts(String address, double weight) {
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("address", address);
    requestBody.put("weight", weight);
    return webClient.post()
             .uri("https://api.example.com/shipment")
             .header("Content-Type", "application/json")
             .bodyValue(requestBody)
             .retrieve()
             .bodyToMono(Double.class)
             .block();
  }

  private String callCourierApi(String orderId, String address) {
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("address", address);
    requestBody.put("weight", orderId);
    return webClient.post()
             .uri("https://api.example.com/courier")
             .header("Content-Type", "application/json")
             .bodyValue(requestBody)
             .retrieve()
             .bodyToMono(String.class)
             .block();
  }

  private void notify(String email, String trackingNumber, double cost) {
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