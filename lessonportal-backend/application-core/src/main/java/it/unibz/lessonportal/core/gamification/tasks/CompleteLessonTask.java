package it.unibz.lessonportal.core.gamification.tasks;

import it.unibz.gamification.Task;
import it.unibz.gamification.annotations.AddPoints;

public class CompleteLessonTask implements Task {

	@Override
	@AddPoints(20)
	public Object execute() {
		return null;
	}

}
