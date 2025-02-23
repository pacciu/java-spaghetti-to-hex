package com.example.spaghetti2hex.service;

import com.example.spaghetti2hex.ports.CourierPort;
import com.example.spaghetti2hex.ports.NotifierPort;
import com.example.spaghetti2hex.ports.ShippingCostsPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

  private static final Logger LOG = LoggerFactory.getLogger(ShippingService.class);

  private final ShippingCostsPort shippingCostsPort;

  private final CourierPort courierPort;

  private final NotifierPort notifierPort;

  public ShippingService(ShippingCostsPort shippingCostsPort, CourierPort courierPort, NotifierPort notifierPort) {
    this.shippingCostsPort = shippingCostsPort;
    this.courierPort = courierPort;
    this.notifierPort = notifierPort;
  }

  public void processShipment(String orderId, String customerEmail, String address, double weight) {
    try {
      double shippingCost = shippingCostsPort.calculateShippingCosts(address, weight);
      String trackingNumber = courierPort.processCourier(orderId, address);
      notifierPort.notify(customerEmail, trackingNumber, shippingCost);
    } catch (Exception e) {
      LOG.error("While shipping: " + e.getMessage(), e);
    }
  }
}