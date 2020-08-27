package it.unibz.lessonportal.core.mutations;

import java.util.List;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.Lesson;

public class LessonMutations {
	protected static int insert(ConnectionPool pool, String username, Lesson lesson) throws Exception {
		// TODO: test RETURNING id
		String query = "INSERT INTO lessons (title, description, addedBy) VALUES (?, ?, ?)";
		Object[] params = new Object[] {lesson.getTitle(), lesson.getDescription(), username};
		return pool.update(query, params);
	}
	
	protected static int setComplete(ConnectionPool pool, String username, int lessonId) throws Exception {
		String query = "INSERT INTO lessonslearned (username, lesson) VALUES (?, ?)";
		Object[] params = new Object[] {username, lessonId};
		return pool.update(query, params);
	}
	
	protected static int setRating(ConnectionPool pool, String username, int lessonId, String rating) throws Exception {
		String query = "UPDATE lessonslearned SET rating = ?::rating WHERE username = ? AND lesson = ?";
		Object[] params = new Object[] {rating, username, lessonId};
		return pool.update(query, params);
	}
	
	protected static int setComments(ConnectionPool pool, int lessonId, List<Comment> comments) throws Exception {
		String query = "UPDATE lessons SET comments = to_json(?::json) WHERE id = ?";
		Object[] params = new Object[] {Comment.toJSONArrayString(comments), lessonId};
		return pool.update(query, params);
	}
}
