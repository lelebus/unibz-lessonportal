package it.unibz.gamification.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.unibz.gamification.User;
import it.unibz.gamification.exceptions.InvalidInputException;

class UserTest {

	static class addPoints {

		@Test
		void testAddPoints() {
			User user = new User("username");
			assertEquals(0, user.getPoints());

			try {
				user.addPoints(2345);
				assertEquals(2345, user.getPoints());
			} catch (Exception e) {
				fail("Unexpected exception: " + e.getMessage());
			}
		}

		@Test
		void testNegativeInput() {
			Exception thrown = assertThrows(InvalidInputException.class, () -> {
				User user = new User("username");
				user.addPoints(-3);
			});

			assertTrue(thrown.getMessage().contains("negative"));
		}

	}

	static class removePoints {

		@Test
		void testRemovePoints() {
			try {
				User user = new User("username", 1000);
				assertEquals(1000, user.getPoints());

				user.removePoints(300);
				assertEquals(700, user.getPoints());
			} catch (Exception e) {
				fail("Unexpected exception: " + e.getMessage());
			}
		}

		@Test
		void testNegativeInput() {
			Exception thrown = assertThrows(InvalidInputException.class, () -> {
				User user = new User("username");
				user.removePoints(-3);
			});

			assertTrue(thrown.getMessage().contains("negative"));
		}

	}
	
	@Test
	void testSetPoints() {
		User user = new User("username");
		assertEquals(0, user.getPoints());
		
		user.setPoints(10);
		assertEquals(10, user.getPoints());
	}

}