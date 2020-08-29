package it.unibz.lessonportal.core;

import java.util.LinkedHashMap;
import org.mindrot.jbcrypt.BCrypt;

import it.unibz.lessonportal.core.getters.Ranking;
import it.unibz.lessonportal.core.getters.UserGetters;
import it.unibz.lessonportal.core.mutations.UserMutations;

public class User {
	private static int logRounds = 12;
	private int resetCount, points;
	private String username, email, password;
	
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		this.resetCount = 0;
		this.points = 0;
	}
	
	public User(String username, String email, String password, int resetCount, int points) {
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
		public boolean setNewUser(String password) {
			User.this.setPassword(password);
			
			try {
				insert(PortalCore.pool, User.this);
			} catch (Exception e) {
				System.out.println(e.toString());
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
