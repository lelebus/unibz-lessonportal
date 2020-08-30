package it.unibz.lessonportal.core.mutations;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.User;

public class UserMutations {

	public static int insert(ConnectionPool pool, User user) throws Exception {
		String query = "INSERT INTO users (name, username, password, resetcount, points) VALUES (?, ?, ?, ?, ?);";
		Object[] params = new Object[] { user.getName(), user.getUsername(), user.getPassword(), user.getResetCount(),
				user.getPoints() };
		for (Object onj : params) {
			System.out.println(onj.toString());
		}
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
