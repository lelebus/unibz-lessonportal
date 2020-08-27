package it.unibz.lessonportal.core.getters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.mocks.CoreMock;

class RankingTest {

	static CoreMock core;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();

		// cleanUp DB
		String query = "DELETE FROM users WHERE username != ?";
		Object[] params = new Object[] { CoreMock.username };
		core.pool.update(query, params);

		query = "INSERT INTO users (username, email, password, points) VALUES (?, ?, ?, ?)";
		params = new Object[] { "user2", "test@test.it", "password", 1 };
		core.pool.update(query, params);

		query = "INSERT INTO users (username, email, password, points) VALUES (?, ?, ?, ?)";
		params = new Object[] { "user3", "test@test.at", "password", 30 };
		core.pool.update(query, params);
	}

	@Test
	void testGetDescRanking() throws Exception {
		LinkedHashMap<String, Integer> ranking = Ranking.getDesc(core.pool);
		assertTrue(ranking.size() >= 2, "Wrong size of rankingList");

		int max = 0;
		Collection<Integer> values = ranking.values();
		for (int points : values) {
			assertTrue(points > max, "Wrong ranking order");
			max = points;
		}
	}

}
