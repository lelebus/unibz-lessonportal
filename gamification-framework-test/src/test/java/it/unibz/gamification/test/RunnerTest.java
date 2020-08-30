
package it.unibz.gamification.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibz.gamification.GamificationFacade;
import it.unibz.gamification.GamificationFacadeSingleton;
import it.unibz.gamification.Task;
import it.unibz.gamification.User;
import it.unibz.gamification.UserRegistry;
import it.unibz.gamification.test.mocks.listeners.ResetNegativeScore;
import it.unibz.gamification.test.mocks.tasks.MinusTaskMock;
import it.unibz.gamification.test.mocks.tasks.PlusTaskMock;

public class RunnerTest {
	
	static GamificationFacade gamification;

	@BeforeEach
	void setUser() {
		gamification = GamificationFacadeSingleton.getInstance();
		User user = new User("username");
		UserRegistry.setCurrentUser(user);
	}

	@Test
	void testListener() {
		gamification.addCallback(new ResetNegativeScore());
		
		User currentUser = UserRegistry.getCurrentUser();
		assertEquals(0, currentUser.getPoints());
		
		Task task = new MinusTaskMock();
		gamification.execute(task);
		assertEquals(0, currentUser.getPoints());
	}
	
	@Test
	void testAddPoints() {
		User currentUser = UserRegistry.getCurrentUser();
		assertEquals(0, currentUser.getPoints());
		
		Task task = new PlusTaskMock();
		gamification.execute(task);
		assertEquals(3, currentUser.getPoints());
	}
	
	@Test
	void testRemovePoints() {
		User currentUser = UserRegistry.getCurrentUser();
		currentUser.setPoints(5);
		assertEquals(5, currentUser.getPoints());
		
		Task task = new MinusTaskMock();
		gamification.execute(task);
		assertEquals(3, currentUser.getPoints());
	}

}
