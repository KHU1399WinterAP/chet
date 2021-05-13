package main.java.gui;

import main.java.socket.ChetSyncer;
import main.java.socket.Client;
import main.java.utils.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChetRoom extends JFrame {
	public final Client CLIENT;
	
	private JPanel mainPanel;
	private JTextArea chetTextArea;
	private JTextField chetTextField;
	private JButton sendButton;
	
	public ChetRoom(Client client) {
		super("Chet | Chet Room");
		
		this.CLIENT = client;
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		initListeners();
		
		CLIENT.sendRequest("CHET");
		new ChetSyncer(this).start();
	}
	
	public void updateChet(String chet) {
		chetTextArea.setText(chet);
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
		var message = StringUtils.normalize(chetTextField.getText());
		
		if (message.isBlank())
			return;
		
		CLIENT.sendRequest("SEND");
		CLIENT.sendRequest(CLIENT.user.name);
		CLIENT.sendRequest(message);
		
		chetTextField.setText("");
	}
	
	private void sendMessage(ActionEvent e) {
		sendMessage();
	}
}
