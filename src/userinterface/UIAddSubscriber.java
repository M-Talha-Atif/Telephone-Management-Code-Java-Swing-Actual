package userinterface;

import java.awt.EventQueue;
import users.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class UIAddSubscriber extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextBox;
	private JTextField surNameTextBox;
	private JTextField residenceTextBox;
	private JTextField phoneNumberTextBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAddSubscriber frame = new UIAddSubscriber();
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
	public UIAddSubscriber() {
		setTitle("Add Subscriber");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterName = new JLabel("Enter Name:");
		lblEnterName.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterName.setBounds(10, 27, 161, 14);
		contentPane.add(lblEnterName);
		
		nameTextBox = new JTextField();
		nameTextBox.setColumns(10);
		nameTextBox.setBounds(181, 21, 96, 20);
		contentPane.add(nameTextBox);
		
		JLabel lblEnterSurName = new JLabel("Enter Sur Name:");
		lblEnterSurName.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterSurName.setBounds(10, 58, 161, 14);
		contentPane.add(lblEnterSurName);
		
		surNameTextBox = new JTextField();
		surNameTextBox.setColumns(10);
		surNameTextBox.setBounds(181, 52, 96, 20);
		contentPane.add(surNameTextBox);
		
		JLabel lblEnterResidence = new JLabel("Enter Residence:");
		lblEnterResidence.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterResidence.setBounds(10, 92, 175, 14);
		contentPane.add(lblEnterResidence);
		
		residenceTextBox = new JTextField();
		residenceTextBox.setColumns(10);
		residenceTextBox.setBounds(181, 86, 96, 20);
		contentPane.add(residenceTextBox);
		
		JLabel lblEnterPhoneNumber = new JLabel("Enter Phone Number:");
		lblEnterPhoneNumber.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterPhoneNumber.setBounds(10, 129, 175, 14);
		contentPane.add(lblEnterPhoneNumber);
		
		phoneNumberTextBox = new JTextField();
		phoneNumberTextBox.setColumns(10);
		phoneNumberTextBox.setBounds(181, 123, 96, 20);
		contentPane.add(phoneNumberTextBox);
		
		JLabel lblSelectTariffPlan = new JLabel("Select Tariff Plan:");
		lblSelectTariffPlan.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblSelectTariffPlan.setBounds(10, 158, 175, 14);
		
		contentPane.add(lblSelectTariffPlan);
		
		JComboBox<String> comboBoxTariffPlan = new JComboBox<String>();
		comboBoxTariffPlan.setBounds(10, 183, 398, 22); // Adjust the width as needed
		// Clear the combo box before populating it
		comboBoxTariffPlan.removeAllItems();

		// Retrieve tariff plans from the JSON file using Administrator class function
		Administrator admin = new Administrator();
		Map<String, TariffPlan> tariffPlans = admin.loadTariffPlansFromJson("TariffPlans.json");

		// Populate the combo box with tariff plan names
		for (TariffPlan tariffPlan : tariffPlans.values()) {
		    String item = tariffPlan.getName();
		    comboBoxTariffPlan.addItem(item);
		}

		contentPane.add(comboBoxTariffPlan);
		
		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String name = nameTextBox.getText().trim();
			        String surName = surNameTextBox.getText().trim();
			        String residence = residenceTextBox.getText().trim();
			        String phoneNumber = phoneNumberTextBox.getText().trim();
			        String tariffPlanString = (String) comboBoxTariffPlan.getSelectedItem();

			        // Check if any field is empty
			        if (name.isEmpty() || surName.isEmpty() || residence.isEmpty() || phoneNumber.isEmpty() || tariffPlanString == null) {
			            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        try {
			        Administrator admin = new Administrator();
			        admin.loadTariffPlansFromJson("TariffPlans.json");
			        String planName =  tariffPlanString;
			       

			        // Create TariffPlan object with all parameters
			        TariffPlan tariffPlan = admin.isPlanExists(planName);
			        


			        // Create Subscriber object
			        Account account = new Account(tariffPlan); // Assuming you have an Account class
			    
			        

			        // Register subscriber with Administrator
			     
			        admin.registerSubscriber(name, surName, residence, phoneNumber, account);
			     // Show success message
			        JOptionPane.showMessageDialog(null, "New subscriber registered: " + name);

			        // Clear text fields after registration
			        nameTextBox.setText("");
			        surNameTextBox.setText("");
			        residenceTextBox.setText("");
			        phoneNumberTextBox.setText("");
			        comboBoxTariffPlan.setSelectedIndex(-1); // Reset combo box selection
			        }
			        catch (NumberFormatException ex) {
			            // Handle exception if user enters invalid data
			            JOptionPane.showMessageDialog(null, "Invalid numeric values for tariff plan.", "Error", JOptionPane.ERROR_MESSAGE);
			        } catch (Exception ex) {
			            // Handle other exceptions
			            JOptionPane.showMessageDialog(null, "Error registering subscriber: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
			        
			}
		});
		btnSaveData.setBounds(251, 229, 115, 23);
		contentPane.add(btnSaveData);
	}
}
