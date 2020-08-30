package it.unibz.lessonportal.core.mocks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import it.unibz.dbConnector.ConnectionPool;

public class CoreMock {

	public ConnectionPool pool;
	public static final String username = "username", name = "User";
	public static final int lessonId = 13;


	public CoreMock() throws Exception {
		pool = new ConnectionPool("jdbc:postgresql://localhost:5432/testportal", "username", "password");
//		TODO: readProperties
		initDatabase("lessontestportal.sql");
		try {
			initDatabase("lessontestportaldata.sql");
		} catch (Exception e) {
			e.printStackTrace();
			// data has been already inserted previously
		}
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
