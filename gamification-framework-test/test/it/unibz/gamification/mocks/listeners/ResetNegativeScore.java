package it.unibz.gamification.mocks.listeners;

import it.unibz.gamification.GamificationListener;
import it.unibz.gamification.User;
import it.unibz.gamification.UserRegistry;

public class ResetNegativeScore implements GamificationListener {

	@Override
	public void execute() {
		User currentUser = UserRegistry.getCurrentUser();
		if (currentUser.getPoints() < 0) {
			currentUser.setPoints(0);
		}
	}

}
