package it.unibz.lessonportal.core.getters;

import java.sql.ResultSet;
import java.util.LinkedList;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.User;

public class Ranking {
	
	public static LinkedList<User> getDesc(ConnectionPool pool) throws Exception {
		LinkedList<User> rankingList = new LinkedList<User>();

		String query = "SELECT username, points FROM users ORDER BY points ASC;";
		
		ResultSet rs = pool.query(query);
		while (rs.next()) {
			User u = new User(rs.getString("username"));
			u.setPoints(rs.getInt("points"));
			rankingList.add(u);
		}
		rs.getStatement().getConnection().close();

		return rankingList;
	}

}
