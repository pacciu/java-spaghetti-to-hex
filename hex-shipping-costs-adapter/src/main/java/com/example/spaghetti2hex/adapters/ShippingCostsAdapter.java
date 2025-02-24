package com.example.spaghetti2hex.adapters;

import com.example.spaghetti2hex.ports.ShippingCostsPort;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ShippingCostsAdapter implements ShippingCostsPort {

  private final WebClient webClient;

  public ShippingCostsAdapter(@Qualifier("webClientShippingCosts") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public double calculateShippingCosts(String address, double weight) {
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

}
