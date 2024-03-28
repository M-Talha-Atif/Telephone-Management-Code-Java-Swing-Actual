package strategypattern;

public class DebitCardPaymentStrategy implements PaymentStrategy {
    @Override
    public String pay(double amount) {
    	
        return "Paid " + amount + " via debit card.";
    }
}
