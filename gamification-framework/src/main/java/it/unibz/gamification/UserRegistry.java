package it.unibz.gamification;

public class UserRegistry {
	private static ThreadLocal<User> currentUserInstance = new ThreadLocal<User>();

	public static synchronized void setCurrentUser(User user) {
		currentUserInstance.set(user);
	}

	public static synchronized User getCurrentUser() {
		return currentUserInstance.get();
	}
}
