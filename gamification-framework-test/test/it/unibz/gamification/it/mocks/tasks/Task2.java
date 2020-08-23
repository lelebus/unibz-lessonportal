package it.unibz.gamification.it.mocks.tasks;

import it.unibz.gamification.annotations.RemovePoints;
import it.unibz.gamification.Task;

public class Task2 implements Task {

	@Override
	@RemovePoints(2)
	public Object execute() {
		return null;
	}

}
