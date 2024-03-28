package factorypattern;
import users.*;

public class ConcreteTariffPlanFactory implements TariffPlanFactory {
	@Override
    public TariffPlan createTariffPlan(String name, double smsCost, double minutesCost,  double dataConnectionCost,
    		double smsLimit,  double callMinutesLimit, double dataUsageLimit ) {
        return new TariffPlan(name, smsCost, minutesCost,  dataConnectionCost,smsLimit,callMinutesLimit,dataUsageLimit);
    }
}
