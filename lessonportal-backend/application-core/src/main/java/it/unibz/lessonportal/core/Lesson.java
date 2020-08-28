package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.unibz.lessonportal.core.getters.LessonGetters;
import it.unibz.lessonportal.core.mutations.LessonMutations;

public class Lesson {

	private int id;
	private String title, description;
	private LinkedList<Comment> comments;
	String username, rating;

	public Lesson(int id) {
		this.id = id;
	}
	
	public Lesson(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Lesson(int id, String title, String description, LinkedList<Comment> comments) {
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

	public LinkedList<Comment> getComments() {
		return comments;
	}
	
	public static class Query extends LessonGetters {
		public static LearnedLesson getLesson(int id, String username) {
			try {
				return get(PortalCore.pool, id, username);
			} catch (Exception e) {
				System.out.println("ERROR:: getting lesson by id");
				e.printStackTrace();
			}
			return null;
		}

		public static List<LearnedLesson> getLessons(String username) {
			try {
				return getAll(PortalCore.pool, username);
			} catch (Exception e) {
				System.out.println("ERROR:: getting all lessons");
				e.printStackTrace();
			}
			return null;
		}
	}

	public class Mutation extends LessonMutations {
		public int setNewLesson(String username) {
			try {
				return insert(PortalCore.pool, username, Lesson.this);
			} catch (Exception e) {
				System.out.println("ERROR:: creating new lesson");
				e.printStackTrace();
				return -1;
			}
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
