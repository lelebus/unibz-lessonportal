package it.unibz.lessonportal.runner;

import it.unibz.lessonPortal.restAPI.server.Server;
import it.unibz.lessonportal.core.PortalCore;

public class Main {
	public static void main(String[] args) {
		PortalCore core;
		try {
			core = new PortalCore("jdbc:postgresql://localhost:5432/lessonportal", "username", "password");
		} catch (Exception e) {
			System.out.println("Error in connecting to database and create tables.");
			e.printStackTrace();
			return;
		}
		
		Server server = new Server(8080, core);
		server.up();
	}
}
