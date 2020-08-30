package it.unibz.lessonportal.core.mutations;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
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
				int returnedId = LessonMutations.insert(core.pool, username, lesson);
				
				String query = "SELECT title FROM lessons WHERE id = ?";
				ResultSet rs = core.pool.query(query, new Object[] {returnedId});
				rs.next();
				assertEquals(lesson.getTitle(), rs.getString("title"));

				rs.getStatement().getConnection().close();
		});
	}

	@Test
	void testSetComplete() {
		assertDoesNotThrow(() -> {
			String query = "DELETE FROM lessonslearned WHERE lesson = ?";
			core.pool.update(query, new Object[] { lesson.getId() });

			assertEquals(1, LessonMutations.setComplete(core.pool, username, lesson.getId()));
			
			query = "SELECT username FROM lessonslearned WHERE lesson = ?";
			ResultSet rs = core.pool.query(query, new Object[] {lesson.getId()});
			rs.next();
			assertEquals(username, rs.getString("username"));

			rs.getStatement().getConnection().close();
		});
	}

	@Test
	void testSetRating() {
		assertDoesNotThrow(() -> {
			assertEquals(1, LessonMutations.setRating(core.pool, username, lesson.getId(), "like"));
			
			String query = "SELECT rating FROM lessonslearned WHERE lesson = ? AND username = ?";
			ResultSet rs = core.pool.query(query, new Object[] {lesson.getId(), username});
			rs.next();
			assertEquals("like", rs.getString("rating"));

			rs.getStatement().getConnection().close();
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
