package com.example.spaghetti2hex.adapters.notify;

import com.example.spaghetti2hex.ports.NotifierPort;
import org.springframework.kafka.core.KafkaTemplate;

public class NotifierKafkaAdapter implements NotifierPort {

  private static final String TOPIC = "shipping";
  private final KafkaTemplate kafkaTemplate;

  public NotifierKafkaAdapter(KafkaTemplate kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void notify(String email, String trackingNumber, double cost) {

    kafkaTemplate.send(TOPIC, "Your order has been shipped. Tracking number: " + trackingNumber + ". Shipping cost: " + cost + "€");

  }
}
