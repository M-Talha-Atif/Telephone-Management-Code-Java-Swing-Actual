package factorypattern;
import users.*;

public interface SubscriberFactory {
    Subscriber createSubscriber(String name, String surName, String residence, String phoneNumber, Account account);
}
