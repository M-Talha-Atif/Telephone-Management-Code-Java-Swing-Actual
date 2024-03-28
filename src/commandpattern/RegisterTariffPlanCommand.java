package commandpattern;
import users.*;

public class RegisterTariffPlanCommand implements Command {
    private Administrator administrator;
    private String name;
    private double dataConnectionCost;
    private double minutesCost;
    private double smsCost;
    private double callMinutesLimit;
    private double dataUsageLimit;
    private double smsLimit;

    public RegisterTariffPlanCommand(Administrator administrator, String name, double smsCost, double minutesCost,  double dataConnectionCost, double smsLimit,  double callMinutesLimit, double dataUsageLimit) {
        this.administrator = administrator;
        this.name = name;
        this.dataConnectionCost = dataConnectionCost;
        this.minutesCost = minutesCost;
        this.smsCost = smsCost;
        this.smsLimit = smsLimit;
        this.callMinutesLimit = callMinutesLimit;
        this.dataUsageLimit = dataUsageLimit;
    }

    @Override
    public void execute() {
        administrator.registerTariffPlan(name,smsCost, minutesCost,  dataConnectionCost, smsLimit, callMinutesLimit, dataUsageLimit);
    }
}
