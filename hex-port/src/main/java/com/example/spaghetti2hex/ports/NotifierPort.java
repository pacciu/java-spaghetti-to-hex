package com.example.spaghetti2hex.ports;

public interface NotifierPort {

  void notify(String email, String trackingNumber, double cost);
}
