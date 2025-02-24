package com.example.spaghetti2hex.config;

import com.example.spaghetti2hex.adapters.notify.NotifierCompositeAdapter;
import com.example.spaghetti2hex.adapters.notify.NotifierKafkaAdapter;
import com.example.spaghetti2hex.adapters.notify.NotifierMailAdapter;
import com.example.spaghetti2hex.ports.NotifierPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class NotifierConfig {

  @Autowired
  private MailConfig mailConfig;
  @Autowired
  private KafkaTemplate kafkaTemplate;

  @Bean
  public NotifierPort notifierPort() {
    return new NotifierCompositeAdapter(new NotifierMailAdapter(mailConfig.javaMailSender()), new NotifierKafkaAdapter(kafkaTemplate));
  }

}
