package it.unibz.gamification.mocks.tasks;

import it.unibz.gamification.Task;
import it.unibz.gamification.annotations.AddPoints;

public class Task1 implements Task {

	@Override
	@AddPoints(3)
	public Object execute() {
		return null;
	}

}
