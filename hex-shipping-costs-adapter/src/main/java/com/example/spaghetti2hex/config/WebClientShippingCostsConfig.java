package com.example.spaghetti2hex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientShippingCostsConfig {

  @Bean
  public WebClient webClientShippingCosts() {
    return WebClient.builder().build();
  }
}