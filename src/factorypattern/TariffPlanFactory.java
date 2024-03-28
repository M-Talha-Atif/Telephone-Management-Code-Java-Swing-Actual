package factorypattern;
import users.*;

public interface TariffPlanFactory {
	 TariffPlan createTariffPlan(String name, double smsCost, double minutesCost,  double dataConnectionCost, double smsLimit, double callMinutesLimit,  double dataUsageLimit);
}
