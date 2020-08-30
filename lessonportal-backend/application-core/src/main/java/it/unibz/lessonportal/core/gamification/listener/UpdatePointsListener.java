package it.unibz.lessonportal.core.gamification.listener;

import it.unibz.gamification.GamificationListener;
import it.unibz.lessonportal.core.User;
import it.unibz.gamification.UserRegistry;

public class UpdatePointsListener implements GamificationListener {

	@Override
	public void execute() {
		it.unibz.gamification.User currentUser = UserRegistry.getCurrentUser();
		
		System.out.println("GEEET");
		
		System.out.println(currentUser.getPoints());

		User user = User.Query.getUser(currentUser.getUsername());
		System.out.println(user.getUsername());
		user.new Mutation().setPoints(currentUser.getPoints());
	}
	
}
