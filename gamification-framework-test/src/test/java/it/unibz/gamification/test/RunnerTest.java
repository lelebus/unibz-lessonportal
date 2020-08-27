
package it.unibz.gamification.test;

import org.junit.jupiter.api.BeforeEach;

import it.unibz.gamification.User;
import it.unibz.gamification.UserRegistry;

public class RunnerTest {

	@BeforeEach
	void setUser() {
		User user = new User("username");

		UserRegistry.setCurrentUser(user);
	}

//	@Test
//	void add

}
