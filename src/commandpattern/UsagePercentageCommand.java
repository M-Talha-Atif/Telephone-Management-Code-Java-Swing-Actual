package commandpattern;
import users.*;

public class UsagePercentageCommand implements Command {
    private Administrator administrator;
    private Subscriber subscriber;

    public UsagePercentageCommand(Administrator administrator, Subscriber subscriber) {
        this.administrator = administrator;
        this.subscriber = subscriber;
    }

    @Override
    public void execute() {
        // Logic to calculate and display usage percentages for the subscriber
        administrator.showUsagePercentage(subscriber);
    }
}