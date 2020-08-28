package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.unibz.lessonportal.core.mutations.LessonMutations;

public class LearnedLesson extends Lesson {
	
	String username, rating;

	public LearnedLesson(int id, String username) {
		super(id);
		this.username = username;
	}
	
	public LearnedLesson(int id, String title, String description, LinkedList<Comment> comments, String username, String rating) {
		super(id, title, description, comments);
		this.username = username;
		this.rating = rating;
	}
	

	public class Mutation extends LessonMutations {
		public boolean setRating(String rating) {
			LearnedLesson lesson = Lesson.Query.getLesson(LearnedLesson.this.getId(), username);
			String currentRating = lesson.rating;
			
			try {
				int success = setRating(PortalCore.pool, LearnedLesson.this.username, LearnedLesson.this.getId(), rating);
				if (success == 0) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return false;
			}
			
			// TODO: assign points (liked: +5; dislike: -3)
			return true;
		}

		public boolean setComment(String comment) {
			try {
				LinkedList<Comment> comments = LearnedLesson.this.getComments();
				comments.add(new Comment(new Date(), LearnedLesson.this.username, comment));
				
				int success = setComments(PortalCore.pool, LearnedLesson.this.getId(), comments);
				if (success == 0) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return false;
			}
			
			// TODO: assign points (+10)
			return true;
		}
	}

}
