package main.java.app;

import main.java.models.User;

import java.util.ArrayList;

public class AppManager {
	private static final ArrayList<User> USERS = new ArrayList<>();
	
	static {
		USERS.add(new User("Bijan"));
		USERS.add(new User("Reza"));
	}
	
	public static boolean addUser(User user) {
		user.name = user.name.replaceAll(" +", " ");
		user.name = user.name.trim();
		
		if (!isValidName(user.name))
			return false;
		
		USERS.add(user);
		return true;
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
