package it.unibz.gamification;

import java.lang.reflect.Method;
import java.util.ArrayList;

import it.unibz.gamification.annotations.AddPoints;
import it.unibz.gamification.annotations.RemovePoints;

public class GamificationFacade {

	private static GamificationFacade instance = null;
	private ArrayList<GamificationListener> listeners = new ArrayList<>();;

	public static synchronized GamificationFacade getInstance() {
		if (instance == null) {
			instance = new GamificationFacade();
		}
		return instance;
	}

	public void addCallback(GamificationListener listener) {
		listeners.add(listener);
	}

	public void execute(Task task) throws RuntimeException {
		try {
			task.execute();
			
			User currentUser = UserRegistry.getCurrentUser();
			Method taskExecute = task.getClass().getMethod("execute");
			if (taskExecute.isAnnotationPresent(AddPoints.class)) {
				int points = taskExecute.getAnnotation(AddPoints.class).value();
				currentUser.addPoints(points);
			} else if (taskExecute.isAnnotationPresent(RemovePoints.class)) {
				int points = taskExecute.getAnnotation(RemovePoints.class).value();
				currentUser.removePoints(points);
			} else {
				// if no annotation is present
				return;
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not complete task and update points", e);
		}
		
		for (GamificationListener gl : listeners) {
			gl.execute();
		}
	}
}
