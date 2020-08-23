package it.unibz.restAPI.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import it.unibz.restAPI.services.*;

public class HTTPServerVerticle extends AbstractVerticle {

	private HttpServer httpServer = null;

	@Override
	public void start() {
		httpServer = vertx.createHttpServer();
		System.out.println("Server has been started successfully");

		Router router = Router.router(vertx);

		// act as middleware
		router.route().handler(context -> {
			HttpServerRequest req = context.request();

			context.next();
		});

		router.mountSubRouter("/user", new UserEndpoint().router(vertx));
		router.mountSubRouter("/lessons", new LessonEndpoint().router(vertx));
		router.mountSubRouter("/ranking", new RankingEndpoint().router(vertx));

		httpServer.requestHandler(router).listen(8080);
	}

	public void close() throws Exception {
		httpServer.close();
	}
}

