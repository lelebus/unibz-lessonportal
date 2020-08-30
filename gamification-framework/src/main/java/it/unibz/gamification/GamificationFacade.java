package it.unibz.gamification;

import java.lang.reflect.Method;
import java.util.ArrayList;

import it.unibz.gamification.annotations.AddPoints;
import it.unibz.gamification.annotations.RemovePoints;

public class GamificationFacade {

	private ArrayList<GamificationListener> listeners = new ArrayList<>();;

	public void addCallback(GamificationListener listener) {
		listeners.add(listener);
	}

	public void execute(Task task) throws RuntimeException {
		try {
			task.execute();
			System.out.println("HERE");
			User currentUser = UserRegistry.getCurrentUser();
			System.out.println("lost");
			Method taskExecute = task.getClass().getMethod("execute");
			System.out.println(taskExecute == null);
			if (taskExecute.isAnnotationPresent(AddPoints.class)) {
				System.out.println("add");
				int points = taskExecute.getAnnotation(AddPoints.class).value();
				System.out.println("adding points");
				currentUser.addPoints(points);
			} else if (taskExecute.isAnnotationPresent(RemovePoints.class)) {
				System.out.println("WHAT");
				int points = taskExecute.getAnnotation(RemovePoints.class).value();
				currentUser.removePoints(points);
			} else {
				System.out.println("NO ANNOTATIOn");
				// if no annotation is present
				return;
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not complete task and update points", e);
		}
		
		System.out.println("EXECUTING LISTENERERR");
		for (GamificationListener gl : listeners) {
			gl.execute();
		}
	}
}
