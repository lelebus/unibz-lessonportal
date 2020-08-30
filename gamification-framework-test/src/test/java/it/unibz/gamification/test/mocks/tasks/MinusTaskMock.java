package it.unibz.gamification.test.mocks.tasks;

import it.unibz.gamification.annotations.RemovePoints;
import it.unibz.gamification.Task;

public class MinusTaskMock implements Task {

	@Override
	@RemovePoints(2)
	public Object execute() {
		return null;
	}

}