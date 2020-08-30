package it.unibz.lessonportal.core.getters;

import java.sql.ResultSet;
import java.util.LinkedHashMap;

import it.unibz.dbConnector.ConnectionPool;

public class Ranking {
	
	public static LinkedHashMap<String, Integer> getDesc(ConnectionPool pool) throws Exception {
		LinkedHashMap<String, Integer> rankingList = new LinkedHashMap<String, Integer>();
		String query = "SELECT username, points FROM users ORDER BY points DESC;";
		
		ResultSet rs = pool.query(query);
		while (rs.next()) {
			rankingList.put(rs.getString("username"), rs.getInt("points"));
		}
		rs.getStatement().getConnection().close();

		return rankingList;
	}

}
