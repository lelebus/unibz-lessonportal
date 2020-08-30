package it.unibz.lessonportal.core.gamification.tasks;

import it.unibz.gamification.Task;
import it.unibz.gamification.annotations.AddPoints;

public class AddCommentTask implements Task {

	@Override
	@AddPoints(10)
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
