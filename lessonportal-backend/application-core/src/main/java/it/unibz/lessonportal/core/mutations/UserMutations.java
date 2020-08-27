package it.unibz.lessonportal.core.mutations;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.User;

public class UserMutations {

	public static int insert(ConnectionPool pool, User user) throws Exception {
		String query = "INSERT INTO users (username, email, password, resetcount, points) VALUES (?, ?, ?, ?, ?);";
		Object[] params = new Object[] { user.getUsername(), user.getEmail(), user.getPassword(), user.getResetCount(),
				user.getPoints() };
		return pool.update(query, params);
	}

	public static int setPoints(ConnectionPool pool, User user) throws Exception {
		String query = "UPDATE users SET points = ? WHERE username = ?";
		Object[] params = new Object[] { user.getPoints(), user.getUsername() };
		return pool.update(query, params);
	}

	public static int setPassword(ConnectionPool pool, User user) throws Exception {
		String query = "UPDATE users SET password = ?, resetcount = ? WHERE username = ?";
		Object[] params = new Object[] { user.getPoints(), user.getResetCount(), user.getUsername() };
		return pool.update(query, params);
	}
	
}
