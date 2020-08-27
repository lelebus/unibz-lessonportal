package it.unibz.lessonPortal.restAPI.server;

import io.vertx.core.Vertx;
import it.unibz.lessonportal.core.PortalCore;

public class Server {
	
	private int port;
	private PortalCore core;
	
	public Server(int port, PortalCore core) {
		this.port = port;
		this.core = core;
	}
	
	public void up() {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new HTTPServerVerticle(8080));
	}
}
