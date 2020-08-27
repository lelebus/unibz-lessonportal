package it.unibz.lessonPortal.restAPI.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonPortal.restAPI.services.*;

public class HTTPServerVerticle extends AbstractVerticle {

	private HttpServer httpServer = null;
	
	private int port;
	
	public HTTPServerVerticle(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		httpServer = vertx.createHttpServer();
		System.out.println("Server successfully started at port " + port);

		Router router = Router.router(vertx);

		// act as middleware
		router.route().handler(context -> {
			HttpServerRequest req = context.request();
			
			context.next();
		});

		router.mountSubRouter("/user", new UserEndpoint().router(vertx));
		router.mountSubRouter("/lessons", new LessonEndpoint().router(vertx));
		router.mountSubRouter("/ranking", new RankingEndpoint().router(vertx));

		httpServer.requestHandler(router).listen(port);
	}

	public void close() throws Exception {
		httpServer.close();
	}
}

