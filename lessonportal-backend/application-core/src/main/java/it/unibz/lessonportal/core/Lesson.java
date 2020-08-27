package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.List;

import it.unibz.lessonportal.core.getters.LessonGetters;
import it.unibz.lessonportal.core.mutations.LessonMutations;

public class Lesson {

	private int id;
	private String title, description;
	private List<Comment> comments;
	String username, rating;

	public Lesson(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Lesson(int id, String title, String description, List<Comment> comments) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public static class Query extends LessonGetters {
		public static Lesson getLesson(int id) {
			try {
				return get(PortalCore.pool, id);
			} catch (Exception e) {
				System.out.println("ERROR:: getting lesson by id");
				e.printStackTrace();
			}
			return null;
		}

		public static List<Lesson> getLessons() {
			try {
				return getAll(PortalCore.pool);
			} catch (Exception e) {
				System.out.println("ERROR:: getting all lessons");
				e.printStackTrace();
			}
			return null;
		}
	}

	public class Mutation extends LessonMutations {
		public boolean setNewLesson(String username) {
			try {
				insert(PortalCore.pool, username, Lesson.this);
			} catch (Exception e) {
				System.out.println("ERROR:: creating new lesson");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}

		public boolean setLessonComplete(String username) {
			try {
				int success = setComplete(PortalCore.pool, username, Lesson.this.id);
				if (success == 0) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return false;
			}
			
			// TODO: assign points (+20)
			return true;
		}
	}
}
