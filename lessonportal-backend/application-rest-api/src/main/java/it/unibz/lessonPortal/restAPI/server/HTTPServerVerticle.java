package it.unibz.lessonPortal.restAPI.server;

import java.util.HashSet;
import java.util.Set;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import it.unibz.lessonPortal.restAPI.services.*;
import it.unibz.lessonportal.core.User;

public class HTTPServerVerticle extends AbstractVerticle {

	private HttpServer httpServer = null;
	private static Algorithm algorithm = Algorithm.HMAC256("cO8K696Pkjg9iQZp");
	private static String JWTIssuer = "unibz";
	private static long cookieAge = 158132000l;

	private int port;

	public HTTPServerVerticle(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		httpServer = vertx.createHttpServer();
		System.out.println("Server successfully started at port " + port);

		Router router = Router.router(vertx);
		enableCorsSupport(router);

		router.route().handler(BodyHandler.create());

		// authentication middleware
		router.route().handler(context -> {
			System.out.println("REQUEST received");

			String username = null;

			Cookie accessToken = context.getCookie("access-token");
			if (accessToken != null) {
				try {
					JWTVerifier verifier = JWT.require(algorithm).withIssuer(JWTIssuer).build();
					DecodedJWT jwt = verifier.verify(accessToken.getValue());
					username = jwt.getClaim("username").asString();
				} catch (JWTVerificationException exception) {
					// Invalid token
				}
			}

			context.put("user", username);

			context.next();
		});

		router.route(HttpMethod.POST, "/api/login").handler(context -> {
			System.out.println("New login attempt");
			HttpServerResponse res = context.response();

			User user = null;
			try {
				user = User.Query.getUser(context.getBodyAsJson().getString("username"));
			} catch (NullPointerException e) {
				// inexistent user
				res.setStatusCode(401);
				res.end();
				return;
			}

			System.out.println(context.getBodyAsJson().getString("password"));
			if (user.checkPassword(context.getBodyAsJson().getString("password"))) {
				try {
					String token = JWT.create().withIssuer(JWTIssuer).withClaim("username", user.getUsername())
							.withClaim("resetcount", user.getResetCount()).sign(algorithm);
					context.addCookie(Cookie.cookie("access-token", token).setMaxAge(cookieAge).setHttpOnly(true));
					res.setStatusCode(200);
				} catch (JWTCreationException exception) {
					// Invalid Signing configuration / Couldn't convert Claims.
					System.out.println("Error in creating JWT");
				}
			} else {
				// wrong password
				res.setStatusCode(401);
			}

			res.end();
		});

		router.route(HttpMethod.POST, "/api/logout").handler(context -> {
			System.out.println("POST /logout");
			HttpServerResponse res = context.response();

			context.removeCookie("access-token");

			res.setStatusCode(200);
			res.end();
		});

		router.mountSubRouter("/api/users", new UserEndpoint().router(vertx));
		router.mountSubRouter("/api/lessons", new LessonEndpoint().router(vertx));
		router.mountSubRouter("/api/ranking", new RankingEndpoint().router(vertx));

		httpServer.requestHandler(router).listen(port);
	}

	public void close() throws Exception {
		httpServer.close();
	}

	protected void enableCorsSupport(Router router) {
		Set<String> allowHeaders = new HashSet<>();
		allowHeaders.add("x-requested-with");
		allowHeaders.add("Access-Control-Allow-Origin");
		allowHeaders.add("Access-Control-Allow-Credentials");
		allowHeaders.add("origin");
		allowHeaders.add("Content-Type");
		allowHeaders.add("accept");
		Set<HttpMethod> allowMethods = new HashSet<>();
		allowMethods.add(HttpMethod.GET);
		allowMethods.add(HttpMethod.PUT);
		allowMethods.add(HttpMethod.OPTIONS);
		allowMethods.add(HttpMethod.POST);
		allowMethods.add(HttpMethod.DELETE);
		allowMethods.add(HttpMethod.PATCH);

		router.route().handler(CorsHandler.create("http://localhost:8080").allowedHeaders(allowHeaders).allowedMethods(allowMethods).allowCredentials(true));
	}
}
