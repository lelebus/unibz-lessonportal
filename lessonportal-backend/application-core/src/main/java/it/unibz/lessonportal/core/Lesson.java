package it.unibz.lessonportal.core;

import java.util.LinkedList;
import java.util.List;

import it.unibz.gamification.GamificationFacade;
import it.unibz.gamification.GamificationFacadeSingleton;
import it.unibz.gamification.UserRegistry;
import it.unibz.lessonportal.core.gamification.tasks.CompleteLessonTask;
import it.unibz.lessonportal.core.getters.LessonGetters;
import it.unibz.lessonportal.core.mutations.LessonMutations;

public class Lesson {

	private int id;
	private String title, description;
	private LinkedList<Comment> comments;
	String username, rating;

	public Lesson(int id) {
		this.id = id;
		this.comments = new LinkedList<Comment>();
	}

	public Lesson(String title, String description) {
		this.title = title;
		this.description = description;
		if (description == null) {
			this.description = "";
		}
		this.comments = new LinkedList<Comment>();
	}

	public Lesson(int id, String title, String description, LinkedList<Comment> comments) {
		this.id = id;
		this.title = title;
		this.description = description;
		if (description == null) {
			this.description = "";
		}
		this.comments = comments;
		if (comments == null) {
			this.comments = new LinkedList<Comment>();
		}
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
		public boolean setNewLesson(String username) {
			try {
				Lesson.this.id = insert(PortalCore.pool, username, Lesson.this);
			} catch (Exception e) {
				System.out.println("ERROR:: creating new lesson");
				e.printStackTrace();
				return false; // TODO set 500 status in httpserver response
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

			GamificationFacade gamification = GamificationFacadeSingleton.getInstance();
			UserRegistry.setCurrentUser(User.Query.getUser(username));
			gamification.execute(new CompleteLessonTask());
			
			return true;
		}
	}
}
