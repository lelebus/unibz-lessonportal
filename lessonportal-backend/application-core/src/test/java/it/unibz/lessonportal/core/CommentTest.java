package it.unibz.lessonportal.core;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.unibz.lessonportal.core.exceptions.InvalidJSONFieldException;

class CommentTest {

	@Nested
	static class EqualsTest {

		static Comment c1;
		
		@BeforeAll
		static void setUp() throws ParseException {
			c1 = new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), "username", "message");
		}
		
		@Test
		void testSameObject() {
			assertTrue(c1.equals(c1));
		}

		@Test
		void testSameComment() throws ParseException {
			assertTrue(
					c1.equals(new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), "username", "message")));
		}

		@Test
		void testDifferentDate() throws ParseException {
			assertFalse(
					c1.equals(new Comment(PortalCore.dateFormat.parse("11:11:12 11-11-2011"), "username", "message")));
		}

		@Test
		void testDifferentUsername() throws ParseException {
			assertFalse(c1.equals(
					new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), "otherusername", "message")));
		}

		@Test
		void testDifferentMessage() throws ParseException {
			assertFalse(c1.equals(
					new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), "username", "othermessage")));
		}
	}

	@Nested
	static
	class JSONTest {

		static LinkedList<Comment> comments;

		// NOTE: Fields in JSON are ordered alphabetically
		String JSONString = "[{\"message\":\"message\",\"timestamp\":\"11:11:11 11-11-2011\",\"username\":\"username\"},{\"message\":\"message\",\"timestamp\":\"12:12:12 12-12-2012\",\"username\":\"username\"}]";

		@BeforeAll
		static void setUp() throws ParseException {
			comments = new LinkedList<Comment>();
			comments.add(new Comment(PortalCore.dateFormat.parse("11:11:11 11-11-2011"), "username", "message"));
			comments.add(new Comment(PortalCore.dateFormat.parse("12:12:12 12-12-2012"), "username", "message"));
		}


		@Test
		void testToJSONArray() throws ParseException, InvalidJSONFieldException {
			String JSONArrayString = Comment.toJSONArrayString(comments);
			assertEquals(JSONString, JSONArrayString);
		}

		@Test
		void testParseJSONArray() throws ParseException, InvalidJSONFieldException {
			LinkedList<Comment> parsedComments = Comment.parseJSONArrayString(JSONString);
			assertEquals(comments.size(), parsedComments.size());

			for (int i = 0; i < comments.size(); i++) {
				assertEquals(comments.get(i), parsedComments.get(i));
			}
		}
	}

}
