package com.example.spaghetti2hex.config;

import com.example.spaghetti2hex.adapters.notify.NotifierMailAdapter;
import com.example.spaghetti2hex.ports.NotifierPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotifierConfig {

  @Autowired
  private MailConfig mailConfig;

  @Bean
  public NotifierPort notifierPort() {
    return new NotifierMailAdapter(mailConfig.javaMailSender());
  }

}
