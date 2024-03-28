package users;
import com.fasterxml.jackson.annotation.JsonProperty;
public class TariffPlan {

private double smsCost; // per sms cost, like user send 1 sms == $0.1 for that one message
private double callCostPerMinute; // Call cost per minute
private double dataCostPerKiloByte; // data connection charges pet KB
private String name;
private double smsLimit;
private double callMinutesLimit;
private double dataUsageLimit;
private double tariffTotalPrice;

// Default constructor
public TariffPlan() {
}

public TariffPlan(@JsonProperty("name") String name,
        @JsonProperty("smsCost") double smsCost,
        @JsonProperty("callCostPerMinute") double callCostPerMinute,
        @JsonProperty("dataCostPerKiloByte") double dataCostPerKiloByte,
        @JsonProperty("smsLimit") double smsLimit,
        @JsonProperty("callMinutesLimit") double callMinutesLimit,
        @JsonProperty("dataUsageLimit") double dataUsageLimit) {
this.name = name;
this.smsCost = smsCost;
this.callCostPerMinute = callCostPerMinute;
this.dataCostPerKiloByte = dataCostPerKiloByte;
this.smsLimit = smsLimit;
this.callMinutesLimit = callMinutesLimit;
this.dataUsageLimit = dataUsageLimit;
this.tariffTotalPrice = smsCost + callCostPerMinute + dataCostPerKiloByte;
}

// Getters and setters
public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public void setTariffTotalPrice() {
	tariffTotalPrice = smsCost + callCostPerMinute + dataCostPerKiloByte;
}

public double getTariffTotalPrice() {
	return tariffTotalPrice;
}

public void setDataCostPerKiloByte(double dataCostPerKiloByte) {
    this.dataCostPerKiloByte = dataCostPerKiloByte;
}

public double getDataCostPerKiloByte() {
    return dataCostPerKiloByte;
}

public void setCallCostPerMinute(double callCostPerMinute) {
    this.callCostPerMinute = callCostPerMinute;
}

public void setSmsCost(double smsCost) {
    this.smsCost = smsCost;
}

public double getSmsCost() {
    return smsCost;
}

public double getCallCostPerMinute() {
    return callCostPerMinute;
}
//Getter and setter methods for smsLimit
public double getSmsLimit() {
    return smsLimit;
}

public void setSmsLimit(double smsLimit) {
    this.smsLimit = smsLimit;
}

// Getter and setter methods for callMinutesLimit
public double getCallMinutesLimit() {
    return callMinutesLimit;
}

public void setCallMinutesLimit(double callMinutesLimit) {
    this.callMinutesLimit = callMinutesLimit;
}

// Getter and setter methods for dataUsageLimit
public double getDataUsageLimit() {
    return dataUsageLimit;
}

public void setDataUsageLimit(double dataUsageLimit) {
    this.dataUsageLimit = dataUsageLimit;
}
@Override
public String toString() {
    return name + ", " + smsCost + ", " + callCostPerMinute + ", " + dataCostPerKiloByte + ", " + smsLimit+ ", " + callMinutesLimit + ", " + dataUsageLimit;
}

}