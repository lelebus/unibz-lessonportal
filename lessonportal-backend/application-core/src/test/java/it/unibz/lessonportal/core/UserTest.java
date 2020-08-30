package it.unibz.lessonportal.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.exceptions.InvalidInputException;
import it.unibz.lessonportal.core.mocks.CoreMock;

class UserTest {

//	TODO

	static CoreMock core;
	static User user;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();

		// cleanUp DB
		String query = "DELETE FROM users WHERE username != ?";
		Object[] params = new Object[] { CoreMock.username };
		core.pool.update(query, params);
		user = new User(CoreMock.name, CoreMock.username, "password", 0, 0);
	}

	@Nested
	class HashTest {
		@Test
		void testHashPassword() {
			assertDoesNotThrow(() -> {
				String password = "password";
				String hashed = User.hashPassword(password);

				assertNotEquals(password, hashed);
			});
		}

		@Test
		void testMatchingPassword() {
			String password = "password";
			User user = new User("username");
			user.setPassword(password);
			assertTrue(user.checkPassword(password));
		}

		@Test
		void testNotMatchingPassword() {
			String password = "password";
			User user = new User("username");
			user.setPassword(password);
			assertFalse(user.checkPassword("wrongpwd"));
		}
	}
	
	@Test
	void testSetNewUser() throws it.unibz.gamification.exceptions.InvalidInputException, InvalidInputException {
		User newUser = new User("new User", "newuser", "password", 0, 0);
		newUser.new Mutation().setNewUser("password");
		
		assertNotNull(User.Query.getUser("newuser"));
	}
	
	@Test
	void testSetPoints()  {
		User user = User.Query.getUser(CoreMock.username);
		user.new Mutation().setPoints(13);
		assertEquals(13, User.Query.getUser(CoreMock.username).getPoints());
	}

}
