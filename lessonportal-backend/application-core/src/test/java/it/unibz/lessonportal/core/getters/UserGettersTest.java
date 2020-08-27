package it.unibz.lessonportal.core.getters;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.unibz.lessonportal.core.User;
import it.unibz.lessonportal.core.mocks.CoreMock;

class UserGettersTest extends UserGetters {

	static CoreMock core;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();
	}

	@Test
	void testGetUser() throws Exception {
		User u = UserGetters.get(core.pool, CoreMock.username);
		assertEquals(CoreMock.email, u.getEmail());
	}
}
