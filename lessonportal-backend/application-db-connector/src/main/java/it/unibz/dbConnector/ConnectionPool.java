package it.unibz.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
	private static ComboPooledDataSource pool;

	public ConnectionPool(String url, String user, String password) throws Exception {
		pool = new ComboPooledDataSource();
		pool.setJdbcUrl(url);
		pool.setUser(user);
		pool.setPassword(password);
	}

	public void setPoolSize(int min, int max, int acquireIncrement) {
		pool.setMinPoolSize(min);
		pool.setMaxPoolSize(max);
		pool.setAcquireIncrement(acquireIncrement);
	}

	public int getActiveConnections() throws SQLException {
		return pool.getNumConnections();
	}

	public Connection connect() throws Exception {
		return pool.getConnection();
	}

	public ResultSet query(String query) throws Exception {
		Connection conn = connect();
		PreparedStatement stmt = SQLStatement.prepare(conn, query);
		return stmt.executeQuery();
	}

	public ResultSet query(String query, Object[] params) throws Exception {
		Connection conn = connect();
		PreparedStatement stmt = SQLStatement.prepare(conn, query, params);
		return stmt.executeQuery();
	}

	public int update(String query) throws Exception {
		Connection conn = connect();
		PreparedStatement stmt = SQLStatement.prepare(conn, query);
		int updatedRows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return updatedRows;
	}

	public int update(String query, Object[] params) throws Exception {
		Connection conn = connect();
		PreparedStatement stmt = SQLStatement.prepare(conn, query, params);
		int updatedRows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return updatedRows;
	}

	public void close() {
		pool.close();
	}
}
