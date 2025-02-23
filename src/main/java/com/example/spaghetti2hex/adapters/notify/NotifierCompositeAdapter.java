package com.example.spaghetti2hex.adapters.notify;

import com.example.spaghetti2hex.ports.NotifierPort;
import java.util.ArrayList;
import java.util.List;

public class NotifierCompositeAdapter implements NotifierPort {

  private final List<NotifierPort> notifiers = new ArrayList<>();

  public NotifierCompositeAdapter(NotifierPort... notifiers) {
    for (NotifierPort notifier : notifiers) {
      this.notifiers.add(notifier);
    }
  }

  @Override
  public void notify(String email, String trackingNumber, double cost) {
    for (NotifierPort notifier : notifiers) {
      notifier.notify(email, trackingNumber, cost);
    }
  }
}