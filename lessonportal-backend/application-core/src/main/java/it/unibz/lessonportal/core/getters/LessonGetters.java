package it.unibz.lessonportal.core.getters;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.LearnedLesson;
import it.unibz.lessonportal.core.Lesson;
import it.unibz.lessonportal.core.Lesson.Query;

public class LessonGetters {
	protected static ArrayList<LearnedLesson> getAll(ConnectionPool pool, String username) throws Exception {
		ArrayList<LearnedLesson> lessons = new ArrayList<LearnedLesson>();

		String query = "SELECT id, title, description, comments, username, rating FROM lessons l FULL JOIN lessonslearned ll ON l.id = ll.lesson WHERE username = ? OR username IS NULL";
		Object[] params = new Object[] {username};

		ResultSet rs = pool.query(query, params);
		while (rs.next()) {
			LinkedList<Comment> comments = new LinkedList<Comment>();

			try {
				comments = Comment.parseJSONArrayString(rs.getString("comments"));
			} catch (NullPointerException e) {
				// no comments
			}

			lessons.add(new LearnedLesson(rs.getInt("id"), rs.getString("title"), rs.getString("description"), comments, rs.getString("username"), rs.getString("rating")));
		}
		rs.getStatement().getConnection().close();

		return lessons;
	}

	protected static LearnedLesson get(ConnectionPool pool, int id, String username) throws Exception {
		String query = "SELECT id, title, description, comments, username, rating FROM lessons l FULL JOIN lessonslearned ll ON l.id = ll.lesson WHERE (ll.username = ? OR ll.username IS NULL) AND l.id = ?";
		Object[] args = new Object[] { username, id };
		System.out.println(args[0] + "" + args[1]);

		ResultSet rs = pool.query(query, args);
		if (rs.next()) {
			System.out.println("rsnext");
			LinkedList<Comment> comments = new LinkedList<Comment>();

			try {
				System.out.println(rs.getString("comments"));
				comments = Comment.parseJSONArrayString(rs.getString("comments"));
			} catch (NullPointerException e) {
				System.out.println("no comments");
				// no comments
			}

			return new LearnedLesson(rs.getInt("id"), rs.getString("title"), rs.getString("description"), comments, username, rs.getString("rating"));
		}
		rs.getStatement().getConnection().close();

		return null;
	}

}