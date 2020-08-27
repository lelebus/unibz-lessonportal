package it.unibz.lessonportal.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import it.unibz.dbConnector.ConnectionPool;
import it.unibz.lessonportal.core.Lesson.Mutation;

public class PortalCore {

	protected static ConnectionPool pool;
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

	public PortalCore(String dbURL, String dbUser, String dbUserPassword) throws Exception {
		pool = new ConnectionPool(dbURL, dbUser, dbUserPassword);
//		TODO: readProperties
		initDatabase("lessonportal.sql");
	}

	private void initDatabase(String filename) throws Exception {
		String dbInit = readFileToString(filename);
		pool.update(dbInit);

		System.out.println("Successfully created database");
	}

	private String readFileToString(String filename) throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString("UTF-8");
	}
}