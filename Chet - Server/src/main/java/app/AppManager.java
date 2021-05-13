package main.java.app;

import main.java.models.User;
import main.java.socket.ClientHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class AppManager {
	private static final ArrayList<User> USERS = new ArrayList<>();
	private static final ArrayList<ClientHandler> CLIENT_HANDLERS = new ArrayList<>();
	
	public static String chet = "";
	
	public static boolean addUser(User user) {
		user.name = normalize(user.name);
		
		if (!isValidName(user.name))
			return false;
		
		USERS.add(user);
		return true;
	}
	
	public static void addSocket(Socket socket) throws IOException {
		var clientHandler = new ClientHandler(socket);
		CLIENT_HANDLERS.add(clientHandler);
		clientHandler.start();
	}
	
	public static void addMessage(String name, String message) {
		chet += name + ": " + message + "\n";
	}
	
	public static void syncChet() {
		for (var clientHandler : CLIENT_HANDLERS) {
			try {
				clientHandler.DATA_OUTPUT_STREAM.writeUTF(chet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String normalize(String text) {
		text = text.replaceAll(" +", " ");
		return text.trim();
	}
	
	private static boolean isValidName(String name) {
		if (name.isBlank())
			return false;
		
		for (var user : USERS)
			if (user.name.compareToIgnoreCase(name) == 0)
				return false;
		
		return true;
	}
}
