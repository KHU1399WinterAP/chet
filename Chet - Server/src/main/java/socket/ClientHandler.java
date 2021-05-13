package main.java.socket;

import main.java.app.AppManager;
import main.java.models.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	private static final String ACCEPT_MESSAGE = "ACCEPT";
	private static final String REJECT_MESSAGE = "REJECT";
	
	public final Socket SOCKET;
	public final DataInputStream DATA_INPUT_STREAM;
	public final DataOutputStream DATA_OUTPUT_STREAM;
	
	public ClientHandler(Socket socket) throws IOException {
		this.SOCKET = socket;
		System.out.println("Connected to " + socket.getRemoteSocketAddress());
		
		this.DATA_INPUT_STREAM = new DataInputStream(SOCKET.getInputStream());
		this.DATA_OUTPUT_STREAM = new DataOutputStream(SOCKET.getOutputStream());
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				var command = DATA_INPUT_STREAM.readUTF();
				String name;
				
				switch (command) {
					case "LOGIN":
						name = DATA_INPUT_STREAM.readUTF();
						var user = new User(name);
						
						if (AppManager.addUser(user))
							DATA_OUTPUT_STREAM.writeUTF(ACCEPT_MESSAGE);
						else
							DATA_OUTPUT_STREAM.writeUTF(REJECT_MESSAGE);
						
						break;
					case "SEND":
						name = DATA_INPUT_STREAM.readUTF();
						var message = DATA_INPUT_STREAM.readUTF();
						
						AppManager.addMessage(name, message);
						AppManager.syncChet();
						
						break;
					
					case "CHET":
						AppManager.syncChet();
					default:
						// TODO
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
