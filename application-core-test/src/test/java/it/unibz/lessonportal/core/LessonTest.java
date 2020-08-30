package it.unibz.lessonportal.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.exceptions.InvalidInputException;
import it.unibz.lessonportal.core.exceptions.UnauthorizedException;
import it.unibz.lessonportal.core.mocks.CoreMock;

class LessonTest {

	static CoreMock core;
	static Lesson lesson;
	static LinkedList<Comment> comments;

	@BeforeAll
	static void setUp() throws Exception {
		core = new CoreMock();

		// cleanUp DB
		String query = "DELETE FROM users WHERE username != ?";
		Object[] params = new Object[] { CoreMock.username };
		core.pool.update(query, params);

		comments = new LinkedList<Comment>();
		comments.add(new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), CoreMock.username, "message"));
		comments.add(new Comment(PortalCore.dateFormat.parse("12:12:12 12-12-2012"), CoreMock.username, "message"));
		lesson = new LearnedLesson(CoreMock.lessonId, "title", "description", comments, CoreMock.username, "like");
	}

	@Nested
	class setNewLessonTest {

		@Test
		void testInvalidUsername() {
			Lesson lesson = new Lesson("title", "description");
			assertFalse(lesson.new Mutation().setNewLesson("inexistentuser"));
		}

	}

	@Nested
	class setLessonCompleteTest {

		@Test
		void testSetLessonComplete()
				throws InvalidInputException, it.unibz.gamification.exceptions.InvalidInputException {
			User user = new User("newuser", "newuser", "password", 0, 0);
			assertTrue(user.new Mutation().setNewUser("password"));

			Lesson ll = new Lesson(CoreMock.lessonId);
			ll.new Mutation().setLessonComplete("newuser");
			assertEquals(20, User.Query.getUser("newuser").getPoints());
		}

		@Test
		void testInvalidUsername() {
			Lesson ll = new Lesson(CoreMock.lessonId);
			assertFalse(ll.new Mutation().setLessonComplete("inexistentuser"));
		}

	}

	@Nested
	class setRatingTest {

		@BeforeEach
		void resetDBData() {
			LearnedLesson lesson = Lesson.Query.getLesson(CoreMock.lessonId, CoreMock.username);
			lesson.new Mutation().setRating("");
		}

		@Test
		void testSetLike() throws InvalidInputException {
			User user = User.Query.getUser(CoreMock.username);
			assertTrue(user.new Mutation().setPoints(0));

			LearnedLesson ll = Lesson.Query.getLesson(CoreMock.lessonId, CoreMock.username);
			assertEquals("", ll.getRating());

			ll.new Mutation().setRating("like");
			assertEquals(5, User.Query.getUser(CoreMock.username).getPoints());
		}

		@Test
		void testSetDislike() {
			User user = User.Query.getUser(CoreMock.username);
			assertTrue(user.new Mutation().setPoints(5));

			LearnedLesson lesson = Lesson.Query.getLesson(CoreMock.lessonId, CoreMock.username);
			assertEquals("", lesson.getRating());

			lesson.new Mutation().setRating("dislike");
			assertEquals(2, User.Query.getUser(CoreMock.username).getPoints());
		}
		
		@Test
		void testUndoLike() {
			// TODO
		}
		
		@Test
		void testUndoLikeAndDislike() {
			// TODO
		}
		
		@Test
		void testUndoDislike() {
			// TODO
		}
		
		@Test
		void testUndoDislikeAndLike() {
			// TODO
		}

		@Test
		void testInvalidUsername() {
			LearnedLesson ll = new LearnedLesson(CoreMock.lessonId, "inexistentUser");
			System.out.println("LESSON LL" + ll);
			assertFalse(ll.new Mutation().setRating("like"));
		}

	}

	@Nested
	class setCommentTest {

		@Test
		void testSetComment() {
			User user = User.Query.getUser(CoreMock.username);
			assertTrue(user.new Mutation().setPoints(0));

			LearnedLesson lesson = Lesson.Query.getLesson(CoreMock.lessonId, CoreMock.username);
			assertDoesNotThrow(() -> {
				lesson.new Mutation().setComment("message");
			});
			assertEquals(10, User.Query.getUser(CoreMock.username).getPoints());
		}

		@Test
		void testInvalidUsername() {
			LearnedLesson lesson = new LearnedLesson(CoreMock.lessonId, "inexistentUser");
			assertThrows(UnauthorizedException.class, () -> {
				lesson.new Mutation().setComment("new Comment");
			});
		}
		
	}
	
	@Test
	void testGetLesson() {
		fail("todo");
	}
	
	@Test
	void testGetLessons() {
		fail("todo");
	}
}
