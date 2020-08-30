package it.unibz.lessonPortal.dbConnector.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.dbConnector.ConnectionPool;

public class PSQLPoolTest {

	static String url = "jdbc:postgresql://localhost:5432/lessonportal";
	static String user = "username";
	static String pwd = "password";

	@Nested
	static class testPooling {
		static ConnectionPool pool;

		@BeforeAll
		static void setUp() {
			assertDoesNotThrow(() -> {
				pool = new ConnectionPool(url, user, pwd);
			});
		}

		@Test
		void testSuccessfullConnection() {
			assertDoesNotThrow(() -> {
				Connection conn = pool.connect();
				conn.close();
			});
		}

		@Test
		void testMultipleConnection() {
			assertDoesNotThrow(() -> {
				Connection conn = pool.connect();
				Connection newConn = pool.connect();
				assertNotEquals(conn, newConn);

				conn.close();
				newConn.close();
			});
		}
	}

	@Test
	void testExpansion() {
		assertDoesNotThrow(() -> {
			ConnectionPool pool = new ConnectionPool(url, user, pwd);
			pool.setPoolSize(3, 5, 1);

			pool.connect();
			assertEquals(3, pool.getActiveConnections());

			pool.connect();
			pool.connect();
			pool.connect();
			assertEquals(4, pool.getActiveConnections());

			pool.close();
		});
	}
}
