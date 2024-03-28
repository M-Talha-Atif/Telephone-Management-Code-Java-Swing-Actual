package strategypattern;

public class CreditCardPaymentStrategy implements PaymentStrategy {
	   @Override
	    public String pay(double amount) {
	    	
	        return "Paid " + amount + " via credit card.";
	    }
}