package it.unibz.lessonportal.core;

import java.util.LinkedList;

import it.unibz.lessonportal.core.getters.Ranking;
import it.unibz.lessonportal.core.getters.UserGetters;
import it.unibz.lessonportal.core.mutations.UserMutations;

public class User {

	private int resetCount, points;
	private String username, email, password;
	
	User(String username, String email, String password, int resetCount, int points) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.resetCount = resetCount;
		this.points = points;
	}

	public int getResetCount() {
		return resetCount;
	}

	public int getPoints() {
		return points;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public static class RankingQuery extends Ranking {
		public static LinkedList<User> getRanking() {
			try {
				return getDesc(PortalCore.pool);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public static class Query extends UserGetters {
		public static User getUser(String username) {
			try {
				return get(PortalCore.pool, username);
			} catch (Exception e) {
				System.out.println("ERROR:: getting user by username");
				e.printStackTrace();
			}
			return null;			
		}
	}
	
	public class Mutation extends UserMutations {
		public boolean setNewUser() {
			try {
				insert(PortalCore.pool, User.this);
			} catch (Exception e) {
				System.out.println("ERROR:: creating new user");
				e.printStackTrace();
				return false;
			}
			return true;
		}		
		
		public boolean setPoints(int points) {
			int rollbackPoints = User.this.points;
			
			User.this.points = points;
			try {
				setPoints(PortalCore.pool, User.this);
			} catch (Exception e) {
				System.out.println("ERROR:: setting points for user");
				e.printStackTrace();
				
				User.this.points = rollbackPoints;
				return false;
			}
			
			return true;
		}			
		
		public boolean resetPassword(String password) {
			String rollbackPassword = User.this.password;
			int rollbackResetCount = User.this.resetCount;
			
			User.this.password = password;
			User.this.resetCount = rollbackResetCount++;
			try {
				setPassword(PortalCore.pool, User.this);
			} catch (Exception e) {
				System.out.println("ERROR:: resetting password for user");
				e.printStackTrace();

				User.this.password = rollbackPassword;
				User.this.resetCount = rollbackResetCount;
				return false;
			}
			
			return true;
		}		
	}
}
