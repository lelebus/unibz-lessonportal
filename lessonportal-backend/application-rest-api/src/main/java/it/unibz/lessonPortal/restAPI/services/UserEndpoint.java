package it.unibz.lessonPortal.restAPI.services;

import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import io.netty.handler.codec.http.HttpStatusClass;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonPortal.restAPI.server.HTTPServerVerticle;
import it.unibz.lessonportal.core.User;

public class UserEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);

		router.route(HttpMethod.GET, "/me").handler(context -> {
			System.out.println("GET /users/me");

			HttpServerResponse res = context.response();

			User user = User.Query.getUser(context.get("user"));
			if (user.getUsername() == null) {
				res.setStatusCode(401);
				res.end();
			}

			if (user != null) {
				JSONObject jsonUser = new JSONObject();
				jsonUser.put("username", user.getUsername());
				jsonUser.put("email", user.getEmail());
				jsonUser.put("points", user.getPoints());
				res.setStatusCode(200);
				res.end(jsonUser.toString());
			}

			res.setStatusCode(500);
			res.end();
		});

		router.route(HttpMethod.POST, "/new").handler(context -> {
			System.out.println("POST /users/new");
			HttpServerRequest req = context.request();
			HttpServerResponse res = context.response();

			User user = new User(req.getFormAttribute("name"), req.getFormAttribute("email"));
//			try {
			user.new Mutation().setNewUser(req.getFormAttribute("password"));
//			res.setStatusCode(409);
//			res.end("This username already exists");
//			res.end("This email already exists");
		});

		return router;
	}
}