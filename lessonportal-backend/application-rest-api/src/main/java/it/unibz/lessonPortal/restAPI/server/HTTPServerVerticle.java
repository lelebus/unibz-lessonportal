package it.unibz.lessonPortal.restAPI.server;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonPortal.restAPI.services.*;
import it.unibz.lessonportal.core.User;

public class HTTPServerVerticle extends AbstractVerticle {

	private HttpServer httpServer = null;
	public static final int logRounds = 12;
	
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
			
			// TODO: get username from jwt
			String username = "lelebus";
			context.put("user", username);
			
			System.out.println("Request");
			
			context.next();
		});
		
		router.route("/login").handler(context -> {
			HttpServerRequest req = context.request();
			HttpServerResponse res = context.response();
			
			String username = req.getFormAttribute("username");
			User user = User.Query.getUser(username);
			
			if (user.checkPassword(req.getFormAttribute("password"))) {
				// TODO: set jwt cookie
				res.setStatusCode(200);
			} else {
				res.setStatusCode(401);
			}
			
			res.end();
		});

		router.mountSubRouter("/users", new UserEndpoint().router(vertx));
		router.mountSubRouter("/lessons", new LessonEndpoint().router(vertx));
		router.mountSubRouter("/ranking", new RankingEndpoint().router(vertx));

		httpServer.requestHandler(router).listen(port);
	}

	public void close() throws Exception {
		httpServer.close();
	}
}

