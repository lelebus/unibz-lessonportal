package it.unibz.lessonportal.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.mocks.CoreMock;

class UserTest {

//	TODO

	static User user;

	@BeforeAll
	static void setUp() {
		user = new User(CoreMock.username, CoreMock.email, "password", 0, 0);
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
			User user = new User("username", "test@test.test");
			user.setPassword(password);
			assertTrue(user.checkPassword(password));
		}

		@Test
		void testNotMatchingPassword() {
			String password = "password";
			User user = new User("username", "test@test.test");
			user.setPassword(password);
			assertFalse(user.checkPassword("wrongpwd"));
		}
	}

}
