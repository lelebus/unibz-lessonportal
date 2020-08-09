package it.unibz.gamification;

import it.unibz.gamification.exceptions.InvalidInputException;

public class User {

	public String username;
	private int points;

	public User(String username) {
		this.username = username;
		this.points = 0;
	}

	public User(String username, int points) throws InvalidInputException {
		this.username = username;

		if (points < 0) {
			throw new InvalidInputException("Annotation value cannot be negative");
		}
		this.points = points;
	}

	public void addPoints(int points) throws InvalidInputException {
		if (points < 0) {
			throw new InvalidInputException("Annotation value cannot be negative");
		}
		this.points += points;
	}

	public void removePoints(int points) throws InvalidInputException {
		if (points < 0) {
			throw new InvalidInputException("Annotation value cannot be negative");
		}
		this.points -= points;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
