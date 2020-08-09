package it.unibz.gamification;

import org.junit.jupiter.api.BeforeEach;

public class RunnerTest {

	@BeforeEach
	void setUser() {
		User user = new User("username");
		
		UserRegistry.setCurrentUser(user);
	}
	
//	@Test
//	void add
	
}
