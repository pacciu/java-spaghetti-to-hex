package com.example.spaghetti2hex.adapters;

import com.example.spaghetti2hex.ports.CourierPort;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CourierAdapter implements CourierPort {

  private final WebClient webClient;

  public CourierAdapter(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public String processCourier(String orderId, String address) {
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

}
