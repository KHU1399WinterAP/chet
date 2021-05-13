package main.java.gui;

import main.java.models.User;
import main.java.socket.Client;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginMenu extends JFrame {
	private final Client CLIENT;
	
	private JPanel mainPanel;
	private JTextField usernameTextField;
	private JButton loginButton;
	private JLabel errorLabel;
	
	public LoginMenu(Client client) {
		super("Chet | Login Menu");
		
		this.CLIENT = client;
		
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
					
					CLIENT.sendRequest("LOGIN");
					CLIENT.sendRequest(name);
					
					var response = CLIENT.getResponse();
					
					if (response.equals("ACCEPT")) {
						CLIENT.user = new User(name);
						
						ChetRoom chetRoom = new ChetRoom(CLIENT);
						this.setVisible(false);
						chetRoom.setVisible(true);
					} else {
						errorLabel.setText("Username is invalid!");
					}
				}
		);
	}
}
