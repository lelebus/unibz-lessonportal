package it.unibz.lessonportal.core.getters;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.LearnedLesson;
import it.unibz.lessonportal.core.Lesson;
import it.unibz.lessonportal.core.PortalCore;
import it.unibz.lessonportal.core.mocks.CoreMock;

class LessonGettersTest extends LessonGetters {

	static CoreMock core;
	static Lesson lesson;
	static LinkedList<Comment> comments;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();
		comments = new LinkedList<Comment>();
		comments.add(new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), CoreMock.username, "message"));
		comments.add(new Comment(PortalCore.dateFormat.parse("12:12:12 12-12-2012"), CoreMock.username, "message"));
		lesson = new LearnedLesson(CoreMock.lessonId, "title", "description", comments, CoreMock.username, "like");
	}

	@Test
	void testGetById() throws Exception {
		Lesson lesson = LessonGetters.get(core.pool, CoreMock.lessonId, CoreMock.username);
		assertEquals(CoreMock.lessonId, lesson.getId());
	}

	@Test
	void testGetAll() throws Exception {
		int lessonId2 = 1998;
		
		String query = "INSERT INTO lessons (id, title, addedBy) VALUES (?, ?, ?)";
		Object[] params = new Object[] {lessonId2, "newlesson", CoreMock.username};
		core.pool.update(query, params);
		
		ArrayList<LearnedLesson> lessons = LessonGetters.getAll(core.pool, CoreMock.username);
		assertTrue(lessons.size() >= 2);
		
		Boolean foundLesson1 = false, foundLesson2 = false;
		for (Lesson l : lessons) {
			if (l.getId() == CoreMock.lessonId) {
				foundLesson1 = true;
			} else if (l.getId() == lessonId2) {
				foundLesson2 = true;
			}
		}
		assertTrue(foundLesson1);
		assertTrue(foundLesson2);
	}
}
