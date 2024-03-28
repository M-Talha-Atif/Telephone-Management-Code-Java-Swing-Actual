package commandpattern;
import users.*;

public class RegisterSubscriberCommand implements Command {
    private Administrator administrator;
    private String name;
    private String surName;
    private String residence;
    private String phoneNumber;
    private Account account;

    public RegisterSubscriberCommand(Administrator administrator, String name, String surName, String residence, String phoneNumber, Account account) {
        this.administrator = administrator;
        this.name = name;
        this.surName = surName;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    @Override
    public void execute() {
        administrator.registerSubscriber(name, surName, residence, phoneNumber, account);
    }
}