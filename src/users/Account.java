package users;

//import org.json.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	 private double balance;

	    private double totalCallCost;

	    private double totalMessagesCost;

	    private double totalDataUsageCost;
	    
	    private double totalMessages;
	    
	    private double totalCallsMinutes;
	    
	    private double totalDataUsageKBS;

	    private TariffPlan tariffPlan;
	    
	    public Account() {
	        totalCallCost = 0;
	        totalMessagesCost = 0;
	        totalDataUsageCost = 0;
	    }
	    // Getter for totalMessages
	    public double getTotalMessages() {
	        return totalMessages;
	    }

	    // Setter for totalMessages
	    public void setTotalMessages(double totalMessages) {
	        this.totalMessages = totalMessages;
	    }

	    // Getter for totalCalls
	    public double getTotalCallsMinutes() {
	        return totalCallsMinutes;
	    }

	    // Setter for totalCalls
	    public void setTotalCallsMinutes(double totalCalls) {
	        this.totalCallsMinutes = totalCalls;
	    }

	    // Getter for totalDataUsage
	    public double getTotalDataUsageKBS() {
	        return totalDataUsageKBS;
	    }

	    // Setter for totalDataUsage
	    public void setTotalDataUsageKBS(double totalDataUsage) {
	        this.totalDataUsageKBS = totalDataUsage;
	    }
	    

	    public Account(
                @JsonProperty("tariffPlan") TariffPlan tariffPlan) {
	        this.tariffPlan = tariffPlan;
	        totalCallCost = 0;
	        totalMessagesCost = 0;
	        totalDataUsageCost = 0;

	    }
	    public void setTariffPlan(TariffPlan tarriffPlan) {
	        this.tariffPlan = tarriffPlan;
	    }

	    public TariffPlan getTariffPlan() {
	    	
	    	return tariffPlan;
	    }
	    @Override
	    public String toString() {
	        return balance + ", " + totalMessagesCost + ", " + totalCallCost  + ", " + totalDataUsageCost + "," + tariffPlan.toString();
	    }
	    public double calculateMessageCost() {
	        // Retrieve the cost per message from the tariff plan
	        double cost = tariffPlan.getSmsCost();
	        totalMessages +=1;
	        totalMessagesCost += cost;
	        return cost;
	    }

	    public double calculateCallCost(double minutes) {
	        // Calculate the cost of the call based on the duration and rate per minute
	        double cost = minutes * tariffPlan.getCallCostPerMinute();
	        totalCallsMinutes += minutes;
	        totalCallCost += cost;
	        return cost;
	    }

	    public double calculateDataUsageCost(double kilobytes) {
	        // Calculate the cost based on the tariff plan's rate per kilobyte
	        double cost = kilobytes * tariffPlan.getDataCostPerKiloByte();
	        totalDataUsageCost += cost;
	        totalDataUsageKBS += kilobytes;
	        return cost;
	    }
	    public double getTotalCallCost() {
	    	return totalCallCost;
	    }
	    
	    public double getTotalMessagesCost() {
	    	return totalMessagesCost;
	    }
	    
	    public double getTotalDataUsageCost() {
	    	return totalDataUsageCost;
	    }
	    
	    public void setBalance(double balance) {
	        this.balance = balance;
	    }
	    public double getBalance() {
	        return balance;
	    }

	    public void deductBalance(double amount) {
	        balance -= amount;
	    }

	  
	    public void topUp(double amount) {
	        balance += amount;
	    }
	    public void setTotalCallCost(double totalCallCost) {
	        this.totalCallCost = totalCallCost;
	    }

	    public void setTotalMessagesCost(double totalMessagesCost) {
	        this.totalMessagesCost = totalMessagesCost;
	    }

	    public void setTotalDataUsageCost(double totalDataUsageCost) {
	        this.totalDataUsageCost = totalDataUsageCost;
	    }


}
