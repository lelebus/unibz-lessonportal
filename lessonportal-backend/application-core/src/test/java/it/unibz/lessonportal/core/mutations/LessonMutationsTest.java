package it.unibz.lessonportal.core.mutations;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.Lesson;
import it.unibz.lessonportal.core.PortalCore;
import it.unibz.lessonportal.core.mocks.CoreMock;

class LessonMutationsTest extends LessonMutations {

	static CoreMock core;
	// NOTE: username and lessonID are known because they were loaded from CoreMock
	static String username = "username";
	static Lesson lesson;
	static LinkedList<Comment> comments;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();
		comments = new LinkedList<Comment>();
		comments.add(new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), "username", "message"));
		comments.add(new Comment(PortalCore.dateFormat.parse("12:12:12 12-12-2012"), "username", "message"));
		lesson = new Lesson(CoreMock.lessonId, "title", "description", comments);
	}

	@Test
	void testInsert() {
		assertDoesNotThrow(() -> {
			assertEquals(1, LessonMutations.insert(core.pool, username, lesson));
		});
	}

	@Test
	void testSetComplete() {
		assertDoesNotThrow(() -> {
			String query = "DELETE FROM lessonslearned WHERE lesson = ?";
			Object[] params = new Object[] {lesson.getId()};
			core.pool.update(query, params);
			
			assertEquals(1, LessonMutations.setComplete(core.pool, username, lesson.getId()));
		});
	}

	@Test
	void testSetRating() {
		assertDoesNotThrow(() -> {
			assertEquals(1, LessonMutations.setRating(core.pool, username, lesson.getId(), "like"));
		});
	}

	@Test
	void testInvalidRating() {
		Exception thrown = assertThrows(SQLException.class, () -> {
			LessonMutations.setRating(core.pool, username, lesson.getId(), "superlike");
		});
		assertTrue(thrown.toString().contains("invalid input value for enum rating"));
	}

	@Test
	void testSetComments() {
		assertDoesNotThrow(() -> {
			assertEquals(1, LessonMutations.setComments(core.pool, lesson.getId(), comments));
		});
	}

}
