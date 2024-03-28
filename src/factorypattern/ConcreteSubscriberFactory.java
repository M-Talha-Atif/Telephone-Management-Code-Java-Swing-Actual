package factorypattern;
import users.*;
public class ConcreteSubscriberFactory implements SubscriberFactory {
    @Override
    public Subscriber createSubscriber(String name, String surName, String residence, String phoneNumber, Account account) {
        return new Subscriber(name, surName, residence, phoneNumber, account);
    }
}