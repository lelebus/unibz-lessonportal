package it.unibz.lessonportal.core.mutations;

import java.sql.ResultSet;
import java.util.List;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.Lesson;

public class LessonMutations {
	protected static int insert(ConnectionPool pool, String username, Lesson lesson) throws Exception {
		String query = "INSERT INTO lessons (title, description, addedBy) VALUES (?, ?, ?) RETURNING id";
		Object[] params = new Object[] {lesson.getTitle(), lesson.getDescription(), username};
		
		ResultSet rs = pool.query(query, params);
		if (rs.next()) {
			rs.getStatement().getConnection().close();
			return rs.getInt("id");
		}
		rs.getStatement().getConnection().close();

		return -1;
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
