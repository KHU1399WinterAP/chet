package main.java.app;

import main.java.gui.LoginMenu;
import main.java.socket.Client;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			Client client = new Client();
			
			LoginMenu loginMenu = new LoginMenu(client);
			loginMenu.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
