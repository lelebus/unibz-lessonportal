
package it.unibz.gamification.test.mocks.tasks;

import it.unibz.gamification.Task;
import it.unibz.gamification.annotations.AddPoints;

public class PlusTaskMock implements Task {

	@Override
	@AddPoints(3)
	public Object execute() {
		return null;
	}

}