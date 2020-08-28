package it.unibz.lessonPortal.dbConnector.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.dbConnector.ConnectionPool;

public class PSQLPoolTest {

	static String url = "jdbc:postgresql://localhost:5432/lessonportal";
	static String user = "username";
	static String pwd = "password";

	@Nested
	static class testPooling {
		ConnectionPool pool;

		@BeforeAll
		void setUp() {
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

	@Nested
	class testPoolSize {
//		ConnectionPool pool;

//		@BeforeEach
//		void setUp() {
//			assertDoesNotThrow(() -> {
//		}

//		@Test
//		void testMinPoolSize() {
//			assertDoesNotThrow(() -> {
//				Connection c = pool.connect();
//				assertEquals(3, pool.getActiveConnections());
//				c.close();
//			});
//		}

		@Test
		void testExpansion() {
			assertDoesNotThrow(() -> {
				ConnectionPool pool = new ConnectionPool(url, user, pwd);
				pool.setPoolSize(3, 5, 1);
				
				Connection c1 = pool.connect();
				assertEquals(3, pool.getActiveConnections());

				Connection c2 = pool.connect();
				Connection c3 = pool.connect();
				Connection c4 = pool.connect();
				assertEquals(4, pool.getActiveConnections());

				pool.close();
			});
		}

//		@Test
//		@Disabled
//		void testMaxPoolSize() {
//			assertThrows(SQLException.class, () -> {
//				pool.connect();
//				pool.connect();
//				pool.connect();
//				pool.connect();
//				pool.connect();
//
//				pool.close();
//			});
//		}
	}
}
