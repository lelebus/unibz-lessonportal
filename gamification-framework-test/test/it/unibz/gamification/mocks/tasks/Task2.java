package it.unibz.gamification.mocks.tasks;

import it.unibz.gamification.Task;
import it.unibz.gamification.annotations.RemovePoints;

public class Task2 implements Task {

	@Override
	@RemovePoints(2)
	public Object execute() {
		return null;
	}

}
