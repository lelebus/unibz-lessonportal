package it.unibz.lessonPortal.restAPI.server;

import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import it.unibz.lessonPortal.restAPI.services.*;
import it.unibz.lessonportal.core.User;

public class HTTPServerVerticle extends AbstractVerticle {

	private HttpServer httpServer = null;
	private static Algorithm algorithm = Algorithm.HMAC256("cO8K696Pkjg9iQZp");
	private static String JWTIssuer = "unibz";

	private int port;

	public HTTPServerVerticle(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		httpServer = vertx.createHttpServer();
		System.out.println("Server successfully started at port " + port);

		Router router = Router.router(vertx);

		router.route().handler(BodyHandler.create());

		// authentication middleware
		router.route().handler(context -> {
			HttpServerRequest req = context.request();

			String username = null;
			
			Cookie accessToken = context.getCookie("access-token");
			if (accessToken != null) {
				try {
				    JWTVerifier verifier = JWT.require(algorithm)
				        .withIssuer(JWTIssuer)
				        .build();
				    DecodedJWT jwt = verifier.verify(accessToken.getValue());
				    username = jwt.getClaim("username").asString();
				} catch (JWTVerificationException exception) {
					// Invalid token
				}
			}

			context.put("user", username);

			context.next();
		});

		router.route(HttpMethod.POST, "/login").handler(context -> {
			System.out.println("New login attempt");
			HttpServerRequest req = context.request();
			HttpServerResponse res = context.response();

			User user = null;
			try {
				user = User.Query.getUser(req.getFormAttribute("username"));
			} catch (NullPointerException e) {
				System.out.println("User does not exist");
				// inexistent user
				res.setStatusCode(401);
				res.end();
			}

			user.setPassword(req.getFormAttribute("password"));
			if (user.checkPassword(req.getFormAttribute("password"))) {
				try {
					String token = JWT.create()
							.withIssuer(JWTIssuer)
							.withClaim("username", user.getUsername())
							.withClaim("resetcount", user.getResetCount())
							.sign(algorithm);
					
					System.out.println(token);
					context.addCookie(Cookie.cookie("access-token", token));
				} catch (JWTCreationException exception) {
					// Invalid Signing configuration / Couldn't convert Claims.
					System.out.println("Error in creating JWT");
				}

				res.setStatusCode(200);
			} else {
				// wrong password
				System.out.println("Wrong password");
				res.setStatusCode(401);
			}

			res.end();
		});

		router.route(HttpMethod.POST, "/logout").handler(context -> {
			System.out.println("POST /logout");
			HttpServerResponse res = context.response();

			context.removeCookie("access-token");

			res.setStatusCode(200);
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
