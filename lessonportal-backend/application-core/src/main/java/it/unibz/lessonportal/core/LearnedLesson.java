package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.LinkedList;

import it.unibz.gamification.GamificationFacade;
import it.unibz.gamification.GamificationFacadeSingleton;
import it.unibz.gamification.UserRegistry;
import it.unibz.lessonportal.core.exceptions.UnauthorizedException;
import it.unibz.lessonportal.core.gamification.tasks.AddCommentTask;
import it.unibz.lessonportal.core.gamification.tasks.CompleteLessonTask;
import it.unibz.lessonportal.core.gamification.tasks.RecommendationTask;
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
			if (lesson == null) 
				return false;
			
			String currentRating = lesson.rating;
			System.out.println("RATING:" + currentRating);
			System.out.println(currentRating == null);
			if (currentRating.equals(rating))
				return true;
			
			System.out.println("uberlebt");

			try {
				int success = setRating(PortalCore.pool, LearnedLesson.this.username, LearnedLesson.this.getId(),
						rating);
				System.out.println(success);
				if (success == 0) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return false;
			}

			// TODO: update likes, dislikes of lesson

			GamificationFacade gamification = GamificationFacadeSingleton.getInstance();
			UserRegistry.setCurrentUser(User.Query.getUser(username));
			gamification.execute(new RecommendationTask(currentRating, rating));

			LearnedLesson.this.rating = rating;

			return true;
		}

		public LinkedList<Comment> setComment(String comment) throws UnauthorizedException {
			LearnedLesson lesson = LearnedLesson.Query.getLesson(LearnedLesson.this.getId(),
					LearnedLesson.this.getUsername());
			if (lesson == null) {
				throw new UnauthorizedException("Cannot add a comment for a lesson that was not completed yet.");
			}

			LinkedList<Comment> comments = lesson.getComments();
			comments.add(new Comment(new Date(), LearnedLesson.this.username, comment));
			try {
				setComments(PortalCore.pool, LearnedLesson.this.getId(), comments);
			} catch (Exception e) {
				System.out.println("ERROR:: completing a lesson");
				e.printStackTrace();
				return null;
			}


			GamificationFacade gamification = GamificationFacadeSingleton.getInstance();
			UserRegistry.setCurrentUser(User.Query.getUser(username));
			gamification.execute(new AddCommentTask());
			
			return comments;
		}
	}

}
