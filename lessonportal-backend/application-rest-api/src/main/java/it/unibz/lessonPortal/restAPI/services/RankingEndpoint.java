package it.unibz.lessonPortal.restAPI.services;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import it.unibz.lessonportal.core.Lesson;
import it.unibz.lessonportal.core.User;

public class RankingEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);
		
		router.route(HttpMethod.GET, "/").handler(context -> {
			System.out.println("GET /ranking");
			HttpServerResponse res = context.response();
			
			LinkedHashMap<String, Integer> ranking = User.RankingQuery.getRanking();
			if (ranking != null) {
				res.setStatusCode(200);
				res.end(new JSONArray(ranking).toString());				
			} else {
				res.setStatusCode(500);
				res.end();
			}
			
		});
		
		return router;
	}

}
