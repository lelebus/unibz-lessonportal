package it.unibz.lessonportal.core;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.mocks.CoreMock;

class LessonTest {
	
//	TODO
	
	@Nested
	class setNewLessonTest {
		
		@Test
		void testInvalidUsername() {
			// TODO inexistent username
		}
		
	}
	
	@Nested
	class setLessonCompleteTest {

		@Test
		void testSetLessonComplete() {
			// TODO user gets points added
			fail("Not yet implemented");
		}
		
		@Test
		void testInvalidUsername() {
			Lesson lesson = new Lesson("title", "description");
			assertEquals(-1, lesson.new Mutation().setNewLesson("inexistentuser"));
		}
		
	}
	
	@Nested
	class setRatingTest {
		
		@Test
		void testSetLike() {
			// TODO users gets points added
		}
		
		@Test
		void testSetDislike() {
			//TODO users gets points removed
		}
		
		@Test
		void testInvalidUsername() {
			LearnedLesson lesson = new LearnedLesson(CoreMock.lessonId, "inexistentUser");
			assertEquals(-1, lesson.new Mutation().setRating("like"));
		}
		
	}
	
	@Nested
	class setCommentTest {
		
		@Test
		void testSetComment() {
			//TODO users gets points added
		}
		
		@Test
		void testInvalidUsername() {
			LearnedLesson lesson = new LearnedLesson(CoreMock.lessonId, "inexistentUser");
			assertEquals(-1, lesson.new Mutation().setComment("new Comment"));
		}
		
	}

}
