package it.unibz.lessonPortal.restAPI.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonportal.core.User;

public class RankingEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);

		router.route(HttpMethod.GET, "/").handler(context -> {
			System.out.println("GET /ranking");
			HttpServerResponse res = context.response();

			if (context.get("user") == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			LinkedHashMap<String, Integer> ranking = User.RankingQuery.getRanking();
			if (ranking != null) {
				JSONArray rankingList = new JSONArray();
				int position = 1;
				for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
					JSONObject rankingSpot = new JSONObject();
					rankingSpot.put("position", position++);
					rankingSpot.put("username", entry.getKey());
					rankingSpot.put("points", entry.getValue());
					if (entry.getKey().equals(context.get("user"))) {
						rankingSpot.put("currentUser", "selected");
					}

					rankingList.put(rankingSpot);
				}

				res.setStatusCode(200);
				res.putHeader("Content-Type", "application/json");
				res.end(rankingList.toString());
				res.end();
			} else {
				res.setStatusCode(500);
				res.end();
			}

		});

		return router;
	}

}
