package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.unibz.lessonportal.core.exceptions.UnauthorizedException;
import it.unibz.lessonportal.core.mutations.LessonMutations;

public class LearnedLesson extends Lesson {

	String username, rating;

	public LearnedLesson(int id, String username) {
		super(id);
		this.username = username;
	}

	public LearnedLesson(int id, String title, String description, LinkedList<Comment> comments, String username,
			String rating) {
		super(id, title, description, comments);
		this.username = username;
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public String getRating() {
		return rating;
	}

	public class Mutation extends LessonMutations {
		public boolean setRating(String rating) {
			LearnedLesson lesson = Lesson.Query.getLesson(LearnedLesson.this.getId(), username);
			String currentRating = lesson.rating;

			try {
				int success = setRating(PortalCore.pool, LearnedLesson.this.username, LearnedLesson.this.getId(),
						rating);
				if (success == 0) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return false;
			}

			// TODO: update likes, dislikes of lesson

			// TODO: assign points (liked: +5; dislike: -3)

			LearnedLesson.this.rating = rating;

			return true;
		}

		public LinkedList<Comment> setComment(String comment) throws UnauthorizedException {
			LearnedLesson lesson = LearnedLesson.Query.getLesson(LearnedLesson.this.getId(), LearnedLesson.this.getUsername());
			if (lesson == null) {
				throw new UnauthorizedException("Cannot add a comment for a lesson that was not completed yet.");
			}
			
			LinkedList<Comment> comments = lesson.getComments();
			System.out.println(comments.size());
			comments.add(new Comment(new Date(), LearnedLesson.this.username, comment));
			try {
				setComments(PortalCore.pool, LearnedLesson.this.getId(), comments);
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return null;
			}

			// TODO: assign points (+10)
			return comments;
		}
	}

}
