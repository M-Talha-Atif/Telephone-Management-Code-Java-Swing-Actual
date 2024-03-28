package userinterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIAdminstratorEnd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAdminstratorEnd frame = new UIAdminstratorEnd();
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
	public UIAdminstratorEnd() {
		setTitle("Adminstrator Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddSubscriber = new JButton("Add Subscriber");
		btnAddSubscriber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIAddSubscriber add = new UIAddSubscriber();
				add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				add.setVisible(true);
			}
		});
		btnAddSubscriber.setBounds(120, 58, 154, 23);
		contentPane.add(btnAddSubscriber);
		
		JButton btnAddPromotion = new JButton("Add Promotion");
		btnAddPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIAddPromotion add = new UIAddPromotion();
				add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				add.setVisible(true);
			}
		});
		btnAddPromotion.setBounds(120, 106, 154, 23);
		contentPane.add(btnAddPromotion);
		
		JButton btnShowUsersUsage = new JButton("Show Users Usage");
		btnShowUsersUsage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsageSubscribers use = new UIUsageSubscribers();
				use.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				use.setVisible(true);
			}
		});
		btnShowUsersUsage.setBounds(120, 157, 154, 23);
		contentPane.add(btnShowUsersUsage);
	}

}
