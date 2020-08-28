package it.unibz.lessonportal.core.mutations;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.User;
import it.unibz.lessonportal.core.mocks.CoreMock;

class UserMutationsTest extends UserMutations {

	static CoreMock core;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();
		// cleanUp DB
		String query = "DELETE FROM users WHERE username != ?";
		Object[] params = new Object[] { CoreMock.username };
		core.pool.update(query, params);
	}

	@Nested
	class insertionTest {

		@Test
		void testInsert() {
			assertDoesNotThrow(() -> {
				User newUser = new User("newuser", "new@user.test", "password", 0, 0);
				assertEquals(1, UserMutations.insert(core.pool, newUser));
				
				String query = "SELECT email FROM users WHERE username = ?";
				ResultSet rs = core.pool.query(query, new Object[] {"newuser"});
				rs.next();
				assertEquals("new@user.test", rs.getString("email"));

				rs.getStatement().getConnection().close();
			});
		}

		@Test
		void testUniqueUsername() {
			Exception thrown = assertThrows(SQLException.class, () -> {
				User newUser = new User(CoreMock.username, "unique@username.test", "password", 0, 0);
				UserMutations.insert(core.pool, newUser);
			});
			assertTrue(thrown.toString().contains("unique constraint \"users_pkey\""));
		}

		@Test
		void testUniqueEmail() {
			Exception thrown = assertThrows(SQLException.class, () -> {
				User newUser = new User("uniqueEmailUser", "test@test.test", "password", 0, 0);
				UserMutations.insert(core.pool, newUser);
			});
			assertTrue(thrown.toString().contains("unique constraint \"users_email_key\""));
		}

	}
	
	@Test
	void testSetPoints() {
		assertDoesNotThrow(() -> {
			User newUser = new User(CoreMock.username, "test@test.test", "password", 0, 10);
			assertEquals(1, UserMutations.setPoints(core.pool, newUser));
			
			String query = "SELECT points FROM users WHERE username = ?";
			ResultSet rs = core.pool.query(query, new Object[] {CoreMock.username});
			rs.next();
			assertEquals(10, rs.getInt("points"));

			rs.getStatement().getConnection().close();
		});
	}
	
	@Test
	void testSetPassword() {
		assertDoesNotThrow(() -> {
			User newUser = new User(CoreMock.username, "test@test.test", "password", 1, 0);
			assertEquals(1, UserMutations.setPassword(core.pool, newUser));
			
			String query = "SELECT resetcount FROM users WHERE username = ?";
			ResultSet rs = core.pool.query(query, new Object[] {CoreMock.username});
			rs.next();
			assertEquals(1, rs.getInt("resetcount"));

			rs.getStatement().getConnection().close();
		});
	}

}
