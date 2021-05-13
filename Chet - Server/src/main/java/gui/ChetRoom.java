package main.java.gui;

import main.java.app.AppManager;
import main.java.models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChetRoom extends JFrame {
	private final User USER;
	
	private JPanel mainPanel;
	private JTextArea chetTextArea;
	private JTextField chetTextField;
	private JButton sendButton;
	
	public ChetRoom(User user) {
		super("Chet | Chet Room");
		
		this.USER = user;
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		initListeners();
	}
	
	private void initListeners() {
		initSendButtonListeners();
		initChetTextFieldListeners();
	}
	
	private void initSendButtonListeners() {
		sendButton.addActionListener(this::sendMessage);
	}
	
	private void initChetTextFieldListeners() {
		chetTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					sendMessage();
			}
		});
	}
	
	private void sendMessage() {
		var message = AppManager.normalize(chetTextField.getText());
		
		if (message.isBlank())
			return;
		
		// TODO: send a request to server
		
		var oldChet = chetTextArea.getText();
		chetTextArea.setText(oldChet + "\n" + USER.name + ": " + message);
		
		chetTextField.setText("");
	}
	
	private void sendMessage(ActionEvent e) {
		sendMessage();
	}
}
