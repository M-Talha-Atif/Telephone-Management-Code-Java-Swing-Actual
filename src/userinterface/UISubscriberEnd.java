package userinterface;

import users.Administrator;
import observerpattern.*;
import users.Subscriber;
import users.TariffPlan;
import strategypattern.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import strategypattern.PaymentStrategy;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class UISubscriberEnd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField kbTextField;
	private JTextField balanceTextField;
	private JTextField callMinutesTextField;
	private static String phoneNumber="";
	private static Subscriber user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UISubscriberEnd frame = new UISubscriberEnd(phoneNumber,user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UISubscriberEnd(String phoneNumber, Subscriber user) {
        UISubscriberEnd.phoneNumber = phoneNumber;
        UISubscriberEnd.user =user;
		setTitle("Subscribers Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Call Minutes:");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 161, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterTopUp = new JLabel("Enter Top Up Balance:");
		lblEnterTopUp.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterTopUp.setBounds(10, 45, 161, 14);
		contentPane.add(lblEnterTopUp);
		
		JLabel lblEnterKbFor = new JLabel("Enter KiloBytes:");
		lblEnterKbFor.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterKbFor.setBounds(10, 83, 175, 14);
		contentPane.add(lblEnterKbFor);
		
		JLabel lblSelectTariffPlan = new JLabel("Select Tariff Plan:");
		lblSelectTariffPlan.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblSelectTariffPlan.setBounds(10, 121, 175, 14);
		contentPane.add(lblSelectTariffPlan);
		
		kbTextField = new JTextField();
		kbTextField.setColumns(10);
		kbTextField.setBounds(181, 77, 96, 20);
		contentPane.add(kbTextField);
		
		balanceTextField = new JTextField();
		balanceTextField.setColumns(10);
		balanceTextField.setBounds(181, 39, 96, 20);
		contentPane.add(balanceTextField);
		
		callMinutesTextField = new JTextField();
		callMinutesTextField.setColumns(10);
		callMinutesTextField.setBounds(181, 5, 96, 20);
		contentPane.add(callMinutesTextField);
		
		JComboBox<String> comboBoxPaymentMethod = new JComboBox<String>();
		comboBoxPaymentMethod.setBounds(200, 153, 89, 22);
		comboBoxPaymentMethod.addItem("Cash");
		comboBoxPaymentMethod.addItem("Credit Card");
		comboBoxPaymentMethod.addItem("Debit Card");
		contentPane.add(comboBoxPaymentMethod);
		
		JButton btnDataConnection = new JButton("Data Connection");
		btnDataConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve call minutes limit and total call cost
		        double dataUsageLimit = user.getAccount().getTariffPlan().getDataUsageLimit();
		      

		        // Retrieve call minutes entered by the user
		        String kbText = kbTextField.getText().trim();

		        // Check if the call minutes text field is empty
		        if (kbText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter Kilo Bytes for data usage.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Check if the call minutes text is a valid number
		        double kbs;
		        try {
		            kbs = Double.parseDouble(kbText);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Please enter a valid number for Kilo Bytes.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Check if the call minutes exceed the call minutes limit
		        if (kbs > dataUsageLimit) {
		            JOptionPane.showMessageDialog(null, "You have exceeded your data usage limit.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        int check = user.makeDataConnection(kbs);
		        if(check>0) {

		        // Register the updated subscriber with the administrator
		        Administrator admin = new Administrator();
		        admin.updateSubscriber(user.getName(), user.getSurName(), user.getResidence(), user.getPhoneNumber(), user.getAccount());
			}
		        else if(check==-1) {
				 JOptionPane.showMessageDialog(null, "Insufficient Balance.", "Error", JOptionPane.ERROR_MESSAGE);
			 }
			        else {
			        	 JOptionPane.showMessageDialog(null, "Limit Exceeded.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			}
		});
		btnDataConnection.setBounds(287, 79, 115, 23);
		contentPane.add(btnDataConnection);
		
		JButton btnTopUp = new JButton("Top Up ");
		btnTopUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve the selected payment method
		        String selectedPaymentMethod = (String) comboBoxPaymentMethod.getSelectedItem();

		        // Check if a payment method is selected
		        if (selectedPaymentMethod == null || selectedPaymentMethod.isEmpty()) {
		            // Show a message if no payment method is selected
		            JOptionPane.showMessageDialog(null, "Please select a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
				// Retrieve the amount from the balance text field
		        String balanceStr = balanceTextField.getText().trim();
		        if (balanceStr.isEmpty()) {
		            // Show a message if the balance text field is empty
		            JOptionPane.showMessageDialog(null, "Please enter the amount to top up.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Parse the amount to a double
		        double amount = Double.parseDouble(balanceStr);

		        // Call the topUp function of the Subscriber class
		        user.topUp(amount);
		        
		        SubscriberObserver obj = new SubscriberObserver(user);
		        obj.update(amount);

		        // Update the subscriber's information
		        Administrator admin = new Administrator();
		        admin.updateSubscriber(user.getName(), user.getSurName(), user.getResidence(), user.getPhoneNumber(), user.getAccount());
		     // Based on the selected payment method, create the corresponding payment strategy object
		        PaymentStrategy paymentStrategy;
		        switch (selectedPaymentMethod) {
		            case "Cash":
		                paymentStrategy = new CashPaymentStrategy();
		                break;
		            case "Credit Card":
		                paymentStrategy = new CreditCardPaymentStrategy();
		                break;
		            case "Debit Card":
		                paymentStrategy = new DebitCardPaymentStrategy();
		                break;
		            default:
		                // This should not happen, but in case an invalid payment method is selected
		                JOptionPane.showMessageDialog(null, "Invalid payment method selected.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		        }

		        // Use the selected payment strategy to perform the payment and display the result
		        String paymentResult = paymentStrategy.pay(amount);
		        JOptionPane.showMessageDialog(null, paymentResult);
			}
		});
		btnTopUp.setBounds(297, 39, 115, 23);
		contentPane.add(btnTopUp);
		
		JButton btnMakeACall = new JButton("Make a Call");
		btnMakeACall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve call minutes limit and total call cost
		        double callMinutesLimit = user.getAccount().getTariffPlan().getCallMinutesLimit();

		        // Retrieve call minutes entered by the user
		        String callMinutesText = callMinutesTextField.getText().trim();

		        // Check if the call minutes text field is empty
		        if (callMinutesText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter call minutes.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Check if the call minutes text is a valid number
		        double callMinutes;
		        try {
		            callMinutes = Double.parseDouble(callMinutesText);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Please enter a valid number for call minutes.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Check if the call minutes exceed the call minutes limit
		        if (callMinutes > callMinutesLimit) {
		            JOptionPane.showMessageDialog(null, "Kindly enter minutes less than your limit. You have exceeded your call minutes limit: " + callMinutesLimit, "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		
		        // Make the call by calling the makeCall function of the subscriber
		        int check = user.makeCall(callMinutes);
		        if(check>0) {

		        // Register the updated subscriber with the administrator
		        Administrator admin = new Administrator();
		        admin.updateSubscriber(user.getName(), user.getSurName(), user.getResidence(), user.getPhoneNumber(), user.getAccount());
	            
			}
		        else if(check==-1) {
				 JOptionPane.showMessageDialog(null, "Insufficient Balance.", "Error", JOptionPane.ERROR_MESSAGE);
			 }
			        else {
			        	 JOptionPane.showMessageDialog(null, "Limit Exceeded.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			}
		});
		btnMakeACall.setBounds(297, 5, 115, 23);
		contentPane.add(btnMakeACall);
		
		JComboBox<String> comboBoxTariffPlan = new JComboBox<String>();
		comboBoxTariffPlan.setBounds(200, 117, 89, 22);
		Administrator admin = new Administrator();
		Map<String, TariffPlan> tariffPlans = admin.loadTariffPlansFromJson("TariffPlans.json");

		// Populate the combo box with tariff plan names
		for (TariffPlan tariffPlan : tariffPlans.values()) {
		    String item = tariffPlan.getName();
		    comboBoxTariffPlan.addItem(item);
		}
		contentPane.add(comboBoxTariffPlan);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = user.sendMessage();
				 if(check>0) {

				        // Register the updated subscriber with the administrator
				        Administrator admin = new Administrator();
				        admin.updateSubscriber(user.getName(), user.getSurName(), user.getResidence(), user.getPhoneNumber(), user.getAccount());
					}
				 else if(check==-1) {
					 JOptionPane.showMessageDialog(null, "Insufficient Balance.", "Error", JOptionPane.ERROR_MESSAGE);
				 }
				        else {
				        	 JOptionPane.showMessageDialog(null, "Limit Exceeded.", "Error", JOptionPane.ERROR_MESSAGE);
				        }
			}
		});
		btnSendMessage.setBounds(45, 229, 115, 23);
		contentPane.add(btnSendMessage);
		
		JButton btnShowBalance = new JButton("Show Balance");
		btnShowBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Balance: " + user.getAccount().getBalance(), "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
          
			}
		});
		btnShowBalance.setBounds(245, 229, 115, 23);
		contentPane.add(btnShowBalance);
		
		JLabel lblSelectPaymentMethod = new JLabel("Select Payment Method:");
		lblSelectPaymentMethod.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblSelectPaymentMethod.setBounds(10, 157, 192, 14);
		contentPane.add(lblSelectPaymentMethod);
		

		
		JButton btnSavePlan = new JButton("Save Plan");
		btnSavePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String plan = (String) comboBoxTariffPlan.getSelectedItem();
				 System.out.println(plan);
		           if (plan== null  || plan.isEmpty()) {
			            // Show a message if no payment method is selected
			            JOptionPane.showMessageDialog(null, "Please select a plan to change.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
		           Administrator admin = new Administrator();
		           admin.loadTariffPlansFromJson("TariffPlans.json");
		         
		        
		           user.joinPromotion(admin.isPlanExists(plan));
		           admin.updateSubscriber(user.getName(), user.getSurName(), user.getResidence(), user.getPhoneNumber(), user.getAccount());
		            
 

			}
		});
		btnSavePlan.setBounds(297, 117, 160, 23);
		contentPane.add(btnSavePlan);
	}
}
