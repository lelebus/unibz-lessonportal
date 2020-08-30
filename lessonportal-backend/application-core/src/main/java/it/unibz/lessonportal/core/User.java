package it.unibz.lessonportal.core;

import java.util.LinkedHashMap;
import org.mindrot.jbcrypt.BCrypt;

import it.unibz.lessonportal.core.exceptions.InvalidInputException;
import it.unibz.lessonportal.core.getters.Ranking;
import it.unibz.lessonportal.core.getters.UserGetters;
import it.unibz.lessonportal.core.mutations.UserMutations;

public class User extends it.unibz.gamification.User {
	private static int logRounds = 12;
	private int resetCount;
	private String name, password;
	
	public User(String username) {
		super(username);
		this.resetCount = 0;
	}
	
	public User(String name, String username, String password, int resetCount, int points) throws it.unibz.gamification.exceptions.InvalidInputException {
		super(username, points);
		this.name = name;
		this.password = password;
		this.resetCount = resetCount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getResetCount() {
		return resetCount;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = hashPassword(password);
	}
	
	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
	}
	
	public boolean checkPassword(String password) {
		return BCrypt.checkpw(password, this.password);
	}
	

	public static class RankingQuery extends Ranking {
		public static LinkedHashMap<String, Integer> getRanking() {
			try {
				return getDesc(PortalCore.pool);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public static class Query extends UserGetters {
		public static User getUser(String username) throws NullPointerException {
			try {
				return get(PortalCore.pool, username);
			} catch (Exception e) {
				if (e instanceof NullPointerException) {
					throw new NullPointerException();
				}
				System.out.println("ERROR:: getting user by username");
				e.printStackTrace();
			}
			return null;			
		}
	}
	
	public class Mutation extends UserMutations {
		public boolean setNewUser(String password) throws InvalidInputException {
			User.this.setPassword(password);
			try {
				insert(PortalCore.pool, User.this);
			} catch (Exception e) {
				if (e.toString().contains("unique constraint \"users_pkey\"")) {
					throw new InvalidInputException("This username is already associated to an account");
				}
				System.out.println("ERROR:: creating new user");
				e.printStackTrace();
				return false;
			}
			return true;
		}		
		
		public boolean setPoints(int points) {
			int rollbackPoints = User.this.getPoints();
			
			User.super.setPoints(points);
			try {

				System.out.println("before");
				System.out.println(User.this.getUsername());
				setPoints(PortalCore.pool, User.this);

				System.out.println("here");
			} catch (Exception e) {
				System.out.println("ERROR:: setting points for user");
				e.printStackTrace();
				
				User.super.setPoints(rollbackPoints);
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
