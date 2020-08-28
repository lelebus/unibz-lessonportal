package it.unibz.lessonPortal.restAPI.services;

import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonportal.core.User;

public class UserEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);

		router.route(HttpMethod.GET, "/me").handler(context -> {
			System.out.println("GET /me");
			HttpServerResponse res = context.response();

			User user = User.Query.getUser(context.get("user"));
			if (user != null) {
				JSONObject jsonUser = new JSONObject();
				jsonUser.put("username", user.getUsername());
				jsonUser.put("email", user.getEmail());
				jsonUser.put("points", user.getPoints());
				res.setStatusCode(200);
				res.end(jsonUser.toString());
			} else {
				res.setStatusCode(500);
				res.end();
			}

		});
		return router;
	}
}