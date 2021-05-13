package main.java.gui;

import main.java.app.AppManager;
import main.java.models.User;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginMenu extends JFrame {
	private JPanel mainPanel;
	private JTextField usernameTextField;
	private JButton loginButton;
	private JLabel errorLabel;
	
	public LoginMenu() {
		super("Chet | Login Menu");
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		initListeners();
	}
	
	private void initListeners() {
		initUsernameTextFieldListeners();
		initLoginButtonListeners();
	}
	
	private void initUsernameTextFieldListeners() {
		usernameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				errorLabel.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
			
			}
		});
	}
	
	private void initLoginButtonListeners() {
		loginButton.addActionListener(
				e -> {
					var name = usernameTextField.getText();
					var user = new User(name);
					
					if (AppManager.addUser(user)) {
						ChetRoom chetRoom = new ChetRoom(user);
						this.setVisible(false);
						chetRoom.setVisible(true);
					} else {
						errorLabel.setText("Username is invalid!");
					}
				}
		);
	}
}
