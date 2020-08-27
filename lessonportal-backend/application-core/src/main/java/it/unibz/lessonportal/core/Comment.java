package it.unibz.lessonportal.core;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unibz.lessonportal.core.exceptions.InvalidJSONFieldException;

public class Comment {
	
	private String username, message;
	private Date timestamp;
	
	public Comment(Date timestamp, String username, String message) {
		this.timestamp = timestamp;
		this.username = username;
		this.message = message;
	}
	
	public String getUsername() {
		return username;
	}
	public String getMessage() {
		return message;
	}
	public String getTimestamp() {
		return PortalCore.dateFormat.format(timestamp);
	}
	
	public static String toJSONArrayString(List<Comment> comments) throws InvalidJSONFieldException {
		JSONArray commentArray = new JSONArray(comments);
		return commentArray.toString();
	}
	
	public static LinkedList<Comment> parseJSONArrayString(String JSONString) throws InvalidJSONFieldException {
		LinkedList<Comment> comments = new LinkedList<Comment>();
		
		JSONArray commentArray = new JSONArray(JSONString);
		Iterator<Object> commentIterator = commentArray.iterator();

		while (commentIterator.hasNext()) {
			JSONObject comment = new JSONObject(commentIterator.next().toString());
			Date timestamp;
			try {
				timestamp = (PortalCore.dateFormat).parse(comment.getString("timestamp"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new InvalidJSONFieldException("JSON field \"timestamp\" is not as expected");
			}
			comments.add(new Comment(timestamp, comment.getString("username"), comment.getString("message")));
		}
		
		return comments;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		
		if (!(obj instanceof Comment)) return false;
		
		Comment c = (Comment) obj;

		if (!this.getTimestamp().equals(c.getTimestamp())) return false;
		if (!this.username.equals(c.getUsername())) return false;
		if (!this.message.equals(c.getMessage())) return false;
		
		return true;
	}
}
