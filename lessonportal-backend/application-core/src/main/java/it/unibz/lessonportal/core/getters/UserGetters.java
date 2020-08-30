package it.unibz.lessonportal.core.getters;

import java.sql.ResultSet;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.User;

public class UserGetters {

	protected static User get(ConnectionPool pool, String username) throws Exception {
		String query = "SELECT name, password, resetcount, points FROM users WHERE username = ?";
		Object[] args = new Object[] { username };

		User user = null;
		ResultSet rs = pool.query(query, args);
		if (rs.next()) {
			user = new User(rs.getString("name"), username, rs.getString("password"), rs.getInt("resetcount"), rs.getInt("points"));
		}
		rs.getStatement().getConnection().close();

		return user;
	}
	
}
