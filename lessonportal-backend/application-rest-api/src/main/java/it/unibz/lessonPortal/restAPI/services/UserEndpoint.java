package it.unibz.lessonPortal.restAPI.services;

import org.json.JSONObject;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonportal.core.User;
import it.unibz.lessonportal.core.exceptions.InvalidInputException;

public class UserEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);

		router.route(HttpMethod.GET, "/me").handler(context -> {
			System.out.println("GET /users/me");

			HttpServerResponse res = context.response();

			if(context.get("user") == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			JSONObject jsonUser = new JSONObject();
			User user = User.Query.getUser(context.get("user"));
			jsonUser.put("username", user.getUsername());
			jsonUser.put("points", user.getPoints());
			
			res.setStatusCode(200);
			res.putHeader("Content-Type", "application/json");
			res.end(jsonUser.toString());
		});

		router.route(HttpMethod.POST, "/").handler(context -> {
			System.out.println("POST /users");
			HttpServerResponse res = context.response();

			User user = new User(context.getBodyAsJson().getString("username"));
			user.setName(context.getBodyAsJson().getString("name"));
			try {
			user.new Mutation().setNewUser(context.getBodyAsJson().getString("password"));
			res.setStatusCode(200);
			} catch (InvalidInputException e) {
				res.setStatusCode(409);
			}
			
			res.end();
		});

		return router;
	}
}