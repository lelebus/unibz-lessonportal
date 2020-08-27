package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.List;

import it.unibz.lessonportal.core.mutations.LessonMutations;

public class LearnedLesson extends Lesson {
	
	String username, rating;
	
	public LearnedLesson(int id, String title, String description, List<Comment> comments, String username, String rating) {
		super(id, title, description, comments);
		this.username = username;
		this.rating = rating;
	}
	

	public class Mutation extends LessonMutations {
//		public boolean setLessonRating(String username, String rating) {
//			try {
//				int success = setRating(PortalCore.pool, username, Lesson.super.id, rating);
//				if (success == 0) {
//					return false;
//				}
//				
//			} catch (Exception e) {
//				System.out.println("ERROR:: completing a lesson");
//				e.printStackTrace();
//				return false;
//			}
//			
//			// TODO: assign points (liked: +5; dislike: -3)
//			return true;
//		}

//		public boolean setLessonComment(User user, String comment) {
//			try {
//				Lesson.super.comments = Lesson.super.getComments();
//				comments.add(new Comment(new Date(), user.getUsername(), comment));
//				
//				int success = setComments(PortalCore.pool, Lesson.this.id, comments);
//				if (success == 0) {
//					return false;
//				}
//			} catch (Exception e) {
//				System.out.println("ERROR:: completing a lesson");
//				e.printStackTrace();
//				return false;
//			}
//			
//			// TODO: assign points (+10)
//			return true;
//		}
	}

}
