package main.java.gui;

import javax.swing.*;

public class ChetRoom extends JFrame {
	private JPanel mainPanel;
	
	public ChetRoom() {
		super("Chet | Chet Room");
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);

//		initListeners();
	}
}
