package userinterface;

import java.awt.EventQueue;
import users.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIAddPromotion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField callTextField;
	private JTextField messageTextField;
	private JTextField priceKBTextField;
	private JTextField smsLimitTextField;
	private JTextField callMinutesLimitTextField;
	private JTextField dataUsageLimitTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAddPromotion frame = new UIAddPromotion();
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
	public UIAddPromotion() {
		setTitle("Add Promotion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterName = new JLabel("Enter Name:");
		lblEnterName.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterName.setBounds(10, 17, 161, 14);
		contentPane.add(lblEnterName);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(233, 11, 96, 20);
		contentPane.add(nameTextField);
		
		JLabel lblEnterCallCharges = new JLabel("Enter call charges:");
		lblEnterCallCharges.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterCallCharges.setBounds(10, 51, 161, 14);
		contentPane.add(lblEnterCallCharges);
		
		callTextField = new JTextField();
		callTextField.setColumns(10);
		callTextField.setBounds(233, 45, 96, 20);
		contentPane.add(callTextField);
		
		JLabel lblEnterMessageCharges = new JLabel("Enter Message Charges:");
		lblEnterMessageCharges.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterMessageCharges.setBounds(10, 82, 193, 14);
		contentPane.add(lblEnterMessageCharges);
		
		messageTextField = new JTextField();
		messageTextField.setColumns(10);
		messageTextField.setBounds(233, 76, 96, 20);
		contentPane.add(messageTextField);
		
		JLabel lblEnterKiloBytes = new JLabel("Enter price for one kb:");
		lblEnterKiloBytes.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterKiloBytes.setBounds(10, 110, 175, 14);
		contentPane.add(lblEnterKiloBytes);
		
		priceKBTextField = new JTextField();
		priceKBTextField.setColumns(10);
		priceKBTextField.setBounds(233, 107, 96, 20);
		contentPane.add(priceKBTextField);
		
		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Administrator administrator = new Administrator();
		            // Retrieve data from text fields
		            String name = nameTextField.getText();
		            double callCharges = Double.parseDouble(callTextField.getText());
		            double messageCharges = Double.parseDouble(messageTextField.getText());
		            double pricePerKB = Double.parseDouble(priceKBTextField.getText());
		            double smsLimit = Double.parseDouble(smsLimitTextField.getText());
		            double callMinutesLimit = Double.parseDouble(callMinutesLimitTextField.getText());
		            double dataUsageLimit = Double.parseDouble(dataUsageLimitTextField.getText());

		            // Create TariffPlan object
		            administrator.registerTariffPlan(name, messageCharges, callCharges, pricePerKB, smsLimit, callMinutesLimit, dataUsageLimit);

		            // Show success message
		            JOptionPane.showMessageDialog(null, "Data saved successfully.");

		            // Clear text fields after saving
		            nameTextField.setText("");
		            callTextField.setText("");
		            messageTextField.setText("");
		            priceKBTextField.setText("");
		            smsLimitTextField.setText("");
		            callMinutesLimitTextField.setText("");
		            dataUsageLimitTextField.setText("");
		        } catch (NumberFormatException ex) {
		            // Handle exception if user enters invalid data
		            JOptionPane.showMessageDialog(null, "Please enter valid numeric values.");
		        }
			}
		});
		btnSaveData.setBounds(140, 229, 115, 23);
		contentPane.add(btnSaveData);
		
		smsLimitTextField = new JTextField();
		smsLimitTextField.setColumns(10);
		smsLimitTextField.setBounds(233, 135, 96, 20);
		contentPane.add(smsLimitTextField);
		
		JLabel lblEnterLimitFor = new JLabel("Enter limit for messages:");
		lblEnterLimitFor.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterLimitFor.setBounds(10, 138, 193, 14);
		contentPane.add(lblEnterLimitFor);
		
		JLabel lblEnterLimitFor_3 = new JLabel("Enter limit for calls mints:");
		lblEnterLimitFor_3.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterLimitFor_3.setBounds(10, 166, 213, 14);
		contentPane.add(lblEnterLimitFor_3);
		
		callMinutesLimitTextField = new JTextField();
		callMinutesLimitTextField.setColumns(10);
		callMinutesLimitTextField.setBounds(233, 163, 96, 20);
		contentPane.add(callMinutesLimitTextField);
		
		JLabel lblEnterLimitFor_1 = new JLabel("Enter limit for data usage:");
		lblEnterLimitFor_1.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblEnterLimitFor_1.setBounds(10, 194, 213, 14);
		contentPane.add(lblEnterLimitFor_1);
		
		dataUsageLimitTextField = new JTextField();
		dataUsageLimitTextField.setColumns(10);
		dataUsageLimitTextField.setBounds(233, 191, 96, 20);
		contentPane.add(dataUsageLimitTextField);
	}
}
