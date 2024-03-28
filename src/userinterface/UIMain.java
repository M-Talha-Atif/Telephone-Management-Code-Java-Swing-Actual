package userinterface;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import users.Administrator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import users.Subscriber;
import java.awt.event.ActionEvent;

public class UIMain {

	private JFrame frmHomepage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMain window = new UIMain();
					window.frmHomepage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomepage = new JFrame();
		frmHomepage.setTitle("HomePage");
		frmHomepage.setBounds(100, 100, 450, 300);
		frmHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomepage.getContentPane().setLayout(null);
		
		JButton btnSubscriber = new JButton("Subscriber");
		btnSubscriber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Display an input dialog to prompt the user for their phone number
		        String phoneNumber = JOptionPane.showInputDialog(null, "Please enter your phone number:");

		        // Check if the user entered a phone number
		        if (phoneNumber != null && !phoneNumber.isEmpty()) {
		            // Process the entered phone number (e.g., validate, store, etc.)
		            // For now, let's just print it to the console
		            Administrator admin = new Administrator();
		            admin.loadSubscribersFromJson("Subscribers.json");
		            if(admin.isPhoneNumberExists(phoneNumber)) {
		            	 Subscriber user = admin.getSubscriberByPhoneNumber(phoneNumber);
		           
		            	 UISubscriberEnd obj = new UISubscriberEnd(phoneNumber,user);
		            	 obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                 obj.setVisible(true);
		            }
		            else {
		                JOptionPane.showMessageDialog(null, "Wrong Phone Number");
		            }
		            
		           
		            
		        } else {
		            // Handle the case where the user canceled the input dialog or entered an empty string
		            System.out.println("No phone number entered or dialog canceled.");
		        }
			}
		});
		btnSubscriber.setBounds(123, 11, 132, 61);
		frmHomepage.getContentPane().add(btnSubscriber);
		
		JButton btnAdminstrator = new JButton("Adminstrator");
		btnAdminstrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIAdminstratorEnd admin = new UIAdminstratorEnd();
				admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				admin.setVisible(true);
			}
		});
		btnAdminstrator.setBounds(123, 120, 132, 61);
		frmHomepage.getContentPane().add(btnAdminstrator);
	}
}
