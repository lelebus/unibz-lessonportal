package it.unibz.lessonportal.core.gamification.tasks;

import java.util.ArrayList;
import java.util.List;

import it.unibz.gamification.GamificationFacade;
import it.unibz.gamification.GamificationFacadeSingleton;
import it.unibz.gamification.Task;
import it.unibz.gamification.annotations.AddPoints;
import it.unibz.gamification.annotations.RemovePoints;

public class RecommendationTask implements Task {
	
	private final int upVote = 5;
	private final int downVote = 3;
	private List<Task> tasks;
	
	public RecommendationTask(String prevRating, String newRating) {

		System.out.println(prevRating);
		System.out.println(newRating);
		
		tasks = new ArrayList<Task>();
		
		switch(prevRating) {
		case "":
			if (newRating.equals("like")) tasks.add(new Recommend());
			if (newRating.equals("dislike")) tasks.add(new Unrecommend());
			break;
		case "like":
			if (newRating.equals("")) tasks.add(new RecommendUndo());
			else if (newRating.equals("dislike")) {
				tasks.add(new RecommendUndo());
				tasks.add(new Unrecommend());
			}
			break;
		case "dislike":
			if (newRating.equals("")) tasks.add(new UnrecommendUndo());
			else if (newRating.equals("like")) {
				tasks.add(new UnrecommendUndo());
				tasks.add(new Recommend());
			}
			break;
		}
	}
	
	@Override
	public Object execute() {
		for (Task task : tasks) {
			System.out.println("hoppla");
			GamificationFacade gamification = GamificationFacadeSingleton.getInstance();
			gamification.execute(task);
		}
		return true;
	}
	
	public class Recommend implements Task {
		
		@Override
		@AddPoints(upVote)
		public Object execute() {
			System.out.println(upVote);
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class RecommendUndo implements Task {
		
		@Override
		@RemovePoints(upVote)
		public Object execute() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class Unrecommend implements Task {
		
		@Override
		@RemovePoints(downVote)
		public Object execute() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class UnrecommendUndo implements Task {
		
		@Override
		@AddPoints(downVote)
		public Object execute() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}


}
