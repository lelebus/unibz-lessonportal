package it.unibz.lessonportal.core.getters;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.Lesson;

public class LessonGetters {
	protected static ArrayList<Lesson> getAll(ConnectionPool pool) throws Exception {
		ArrayList<Lesson> lessons = new ArrayList<Lesson>();

		String query = "SELECT id, title, description, comments FROM lessons";

		// TODO: get ranking for currentuser
		
		ResultSet rs = pool.query(query);
		while (rs.next()) {
			List<Comment> comments = new LinkedList<Comment>();

			try {
				comments = Comment.parseJSONArrayString(rs.getString("comments"));
			} catch (NullPointerException e) {
				// no comments
			}

			lessons.add(new Lesson(rs.getInt("id"), rs.getString("title"), rs.getString("description"), comments));
		}
		rs.getStatement().getConnection().close();

		return lessons;
	}

	protected static Lesson get(ConnectionPool pool, int id) throws Exception {
		String query = "SELECT id, title, description, comments FROM lessons WHERE id = ?";
		Object[] args = new Object[] { id };

		ResultSet rs = pool.query(query, args);
		if (rs.next()) {
			List<Comment> comments = new LinkedList<Comment>();

			try {
				comments = Comment.parseJSONArrayString(rs.getString("comments"));
			} catch (NullPointerException e) {
				// no comments
			}

			return new Lesson(rs.getInt("id"), rs.getString("title"), rs.getString("description"), comments);
		}
		rs.getStatement().getConnection().close();

		return null;
	}

}
