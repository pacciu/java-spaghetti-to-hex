package com.example.spaghetti2hex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientCourierConfig {

  @Bean
  public WebClient webClientCourier() {
    return WebClient.builder().build();
  }
}