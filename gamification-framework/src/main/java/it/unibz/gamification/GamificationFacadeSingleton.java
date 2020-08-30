package it.unibz.gamification;

public class GamificationFacadeSingleton {
	private static GamificationFacade gamificationFacade;
	
	public static synchronized GamificationFacade getInstance() {
		if (gamificationFacade == null)
			gamificationFacade = new GamificationFacade();

		return gamificationFacade;
	}
}
