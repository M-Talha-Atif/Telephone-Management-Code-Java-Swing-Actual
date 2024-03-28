package users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subscriber {
	 private String name; // his name
	    private String surName; // his surName
	    private String residence; // his living place
	    private String phoneNumber; // his phone number
	    private Account account; // account class object

	    
	    public Subscriber() {}
	   

	  
	    public Subscriber(@JsonProperty("name") String name,
                @JsonProperty("surName") String surName,
                @JsonProperty("residence") String residence,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("account") Account account
               ) {
	        this.name = name;
	        this.phoneNumber = phoneNumber;
	        this.account = account;
	        this.surName = surName;
	        this.residence = residence;
	      
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setSurName(String surName) {
	        this.surName = surName;
	    }

	    public String getSurName() {
	        return surName;
	    }

	    public void setAccount(Account account) {
	        this.account = account;
	    }

	    public Account getAccount() {
	        return account;
	    }

	    public void setResidence(String residence) {
	        this.residence = residence;
	    }

	    public String getResidence() {
	        return residence;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }
	    
	    public int makeCall(double duration) {
	        double cost =  account.calculateCallCost(duration);
	        double check = account.getBalance() - cost;
	        if(check<0) {
	       
	          return -1;
	        }
	        else if(this.account.getTotalCallsMinutes() > this.account.getTariffPlan().getCallMinutesLimit()) {
	        	return -2;
	        }
	        account.deductBalance(cost);
	        return 1;	
	    }

	    public int sendMessage() {
	        double cost = account.calculateMessageCost();
	    
	        double check = account.getBalance() - cost;
	        if(check<0 ) {
	       
	          return -1;
	        }
	        else if(this.account.getTotalMessages() > this.account.getTariffPlan().getSmsLimit()) {
	        	return -2;
	        }
	        account.deductBalance(cost);
	        return 1;	
	    }

	    public int makeDataConnection(double kilobytes) {
	        double cost = account.calculateDataUsageCost(kilobytes);
	        double check = account.getBalance() - cost;
	        if(check<0 ) {
	 	       
		          return -1;
		        }
		        else if(this.account.getTotalDataUsageKBS() > this.account.getTariffPlan().getDataUsageLimit()) {
		        	return -2;
		        }
		        account.deductBalance(cost);
		        return 1;
	    }

	    public void joinPromotion(TariffPlan plan) {
	    	 account.setTariffPlan(plan);
	    	    this.getAccount().setTotalCallsMinutes(0);
	    	    this.getAccount().setTotalMessages(0);
	    	    this.getAccount().setTotalDataUsageKBS(0);
	    	    this.getAccount().setTotalCallCost(0);
	    	    this.getAccount().setTotalMessagesCost(0);
	    	    this.getAccount().setTotalDataUsageCost(0);
	    }

	    public void topUp(double amount) {
	        account.topUp(amount);
	    }
	    @Override
	    public String toString() {
	        return name + ", " + surName + ", " + residence + ", " + phoneNumber + ", " + account.toString();
	    }

}
