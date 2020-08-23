module gamificationFrameworkTests {
	exports it.unibz.gamification.test;
	exports it.unibz.gamification.test.mocks.listeners;
	exports it.unibz.gamification.it.mocks.tasks;

	requires gamificationFramework;
	requires org.junit.jupiter.api;
}