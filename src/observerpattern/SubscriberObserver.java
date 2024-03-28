package observerpattern;

import users.*;
//SubscriberObserver class for observing changes in subscriber's account balance
public class SubscriberObserver implements AccountObserver {
private Subscriber subscriber;

public SubscriberObserver(Subscriber subscriber) {
   this.subscriber = subscriber;
}

@Override
public String update(double newBalance) {
   return "Account balance updated for subscriber " + subscriber.getName() + ": " + newBalance ;
}
}