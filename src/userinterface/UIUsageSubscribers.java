package userinterface;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.util.Map;

import users.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UIUsageSubscribers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Administrator admin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIUsageSubscribers frame = new UIUsageSubscribers();
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
	public UIUsageSubscribers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 11, 486, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		  // Create an instance of Administrator
        admin = new Administrator();

        // Load subscriber data and populate the table
        loadSubscriberData();
	}
	private void loadSubscriberData() {
	    try {
	        // Load subscribers data
	        Map<String, Subscriber> subscribers = admin.loadSubscribersFromJson("Subscribers.json");

	        // Create a table model with column names
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("Subscriber Name");
	        model.addColumn("Subscriber Phone");
	        model.addColumn("Data Usage (%)");
	        model.addColumn("Call Minutes (%)");
	        model.addColumn("Messages (%)");

	        // Populate the table model with subscriber data
	        for (Subscriber subscriber : subscribers.values()) {
	            String name = subscriber.getName();
	            String phone = subscriber.getPhoneNumber();
	            double totalDataUsage = subscriber.getAccount().getTotalDataUsageKBS();
	            double totalCallMinutes = subscriber.getAccount().getTotalCallsMinutes();
	            double totalMessages = subscriber.getAccount().getTotalMessages();
	            double totalDataUsagePercent = (totalDataUsage / subscriber.getAccount().getTariffPlan().getDataUsageLimit()) * 100; // Assuming TOTAL_DATA_USAGE_LIMIT is defined
	            double totalCallMinutesPercent = (totalCallMinutes / subscriber.getAccount().getTariffPlan().getCallMinutesLimit()) * 100; // Assuming TOTAL_CALL_MINUTES_LIMIT is defined
	            double totalMessagesPercent = (totalMessages / subscriber.getAccount().getTariffPlan().getSmsLimit()) * 100; // Assuming TOTAL_MESSAGES_LIMIT is defined

	            // Add subscriber data to the table model
	            model.addRow(new Object[]{name, phone, 
	                                       totalDataUsagePercent, totalCallMinutesPercent, totalMessagesPercent});
	        }

	        // Set the table model to the JTable
	        table.setModel(model);

	    } catch (Exception e) {
	        e.printStackTrace(); // Handle exception gracefully
	    }
	}

//	private void loadSubscriberData() {
//        try {
//            // Load subscribers data
//        	Map<String, Subscriber> subscribers = admin.loadSubscribersFromJson("Subscribers.json");
//
//
//
//            // Create a table model with column names
//            DefaultTableModel model = new DefaultTableModel();
//            model.addColumn("Subscriber Name");
//            model.addColumn("Subscriber Phone");
//            model.addColumn("Total Data Usage");
//            model.addColumn("Total Call Minutes");
//            model.addColumn("Total Messages");
//
//            // Populate the table model with subscriber data
//            for (Subscriber subscriber : subscribers.values()) {
//                String name = subscriber.getName();
//                String phone = subscriber.getPhoneNumber();
//                double totalDataUsage = subscriber.getAccount().getTotalDataUsageKBS();
//                double totalCallMinutes = subscriber.getAccount().getTotalCallsMinutes();
//                double totalMessages = subscriber.getAccount().getTotalMessages();
//
//                // Add subscriber data to the table model
//                model.addRow(new Object[]{name, phone, totalDataUsage, totalCallMinutes, totalMessages});
//            }
//
//            // Set the table model to the JTable
//            table.setModel(model);
//
//        } catch (Exception e) {
//            e.printStackTrace(); // Handle exception gracefully
//        }
//	}
}
