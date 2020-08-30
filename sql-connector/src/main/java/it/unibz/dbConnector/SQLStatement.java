package it.unibz.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unibz.dbConnector.exceptions.UndefinedTypeException;

public class SQLStatement {
	protected static PreparedStatement prepare(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	protected static PreparedStatement prepare(Connection connection, String query, Object[] params) throws SQLException, UndefinedTypeException {
		PreparedStatement stmt = connection.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			switch (getType(params[i])) {
			case "Integer":
				stmt.setInt(i + 1, (int) params[i]);
				break;
			case "Double":
				stmt.setDouble(i + 1, (int) params[i]);
				break;
			case "String":
				stmt.setString(i + 1, (String) params[i]);
				break;
			case "Boolean":
				stmt.setBoolean(i + 1, (Boolean) params[i]);
				break;
			default:
				throw new UndefinedTypeException("Cannot set value for " + params[i].toString());
			}
		}

		return stmt;
	}

	public static String getType(Object obj) {
		if (obj instanceof Integer) {
			return "Integer";
		} else if (obj instanceof Double) {
			return "Double";
		} else if (obj instanceof String) {
			return "String";
		} else if (obj instanceof Boolean) {
			return "Boolean";
		}
		
		return null;
	}

}
