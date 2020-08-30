package it.unibz.lessonPortal.dbConnector.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibz.dbConnector.SQLStatement;

public class TypeParserTest {

	@Test
	void testInteger() {
		assertEquals("Integer", SQLStatement.getType(15));
		assertEquals("Integer", SQLStatement.getType(-1));
	}

	@Test
	void testDouble() {
		assertEquals("Double", SQLStatement.getType(5.5));
	}

	@Test
	void testString() {
		assertEquals("String", SQLStatement.getType("this is a string"));
	}

	@Test
	void testBoolean() {
		assertEquals("Boolean", SQLStatement.getType(true));
		assertEquals("Boolean", SQLStatement.getType(false));
	}
}
