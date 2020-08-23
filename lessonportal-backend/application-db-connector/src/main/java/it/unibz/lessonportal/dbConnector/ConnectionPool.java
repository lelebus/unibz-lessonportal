package it.unibz.lessonportal.dbConnector;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
	private static ComboPooledDataSource pool;

	public ConnectionPool(String driver, String url, String user, String password) {
		pool = new ComboPooledDataSource();
		try {
			pool.setDriverClass(driver);
			pool.setJdbcUrl(url);
			pool.setUser(user);
			pool.setPassword(password);
		} catch (PropertyVetoException e) {
			System.out.println("Fail. Check the specified driver!");
		}

		System.out.println("Successfully created pool!");
	}
	
	public Connection connect() throws Exception {
		return pool.getConnection();
	}
	
	public ResultSet query(String query, String[] params) throws Exception {
		Connection conn = connect();
		
		PreparedStatement stmt = conn.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			stmt.setString(i, params[i]);
		}
		
		return stmt.executeQuery();
	}

	public void close() {
		pool.close();
	}
}
