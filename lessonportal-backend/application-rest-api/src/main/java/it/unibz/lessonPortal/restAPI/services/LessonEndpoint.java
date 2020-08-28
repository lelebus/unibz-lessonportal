package it.unibz.lessonPortal.restAPI.services;

import java.util.ArrayList;
import java.util.List;

import com.sun.security.jgss.ExtendedGSSContext;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Router;
import it.unibz.lessonPortal.restAPI.server.Server;
import it.unibz.lessonportal.core.Lesson;
import org.json.JSONArray;
import org.json.JSONObject;

public class LessonEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);
		
		router.route(HttpMethod.GET, "/").handler(context -> {
			System.out.println("GET /lessons");
			HttpServerResponse res = context.response();
			
			String username = context.get("user");
			List<Lesson> lessons = Lesson.Query.getLessons();
			
			res.setStatusCode(200);
			res.end(new JSONArray(lessons).toString());
		});
		
		router.routeWithRegex(HttpMethod.GET, "\\/:(?<lessonId>[^\\/]\\d+)").handler(context -> {
			String lessonId = context.request().getParam("lessonId");
			System.out.println("GET /lessons by id " + lessonId);
			HttpServerResponse res = context.response();
			
			String username = context.get("user");
			Lesson lesson = Lesson.Query.getLesson(Integer.parseInt(lessonId));
			
			res.setStatusCode(200);
			res.end(new JSONObject(lesson).toString());
		});

		router.route(HttpMethod.POST, "/").handler(context -> {
			System.out.println("POST /lessons");
			HttpServerRequest req = context.request();
			HttpServerResponse res = context.response();
			
			String username = context.get("user");

			String lessonTitle = req.getParam("title");
			String lessonDescription = req.getParam("description");
			Lesson lesson = new Lesson(lessonTitle, lessonDescription);
			
			int id = lesson.new Mutation().setNewLesson(username);
			if (id >= 0) {
				res.setStatusCode(200);
				res.end(String.valueOf(id));
			} else {
				res.setStatusCode(500);
				res.end();				
			}
		});
		
		router.routeWithRegex(HttpMethod.PUT, "\\/:(?<lessonId>[^\\/]\\d+)").handler(context -> {
			String lessonId = context.request().getParam("lessonId");
			System.out.println("PUT /lessons ranking - lessonId" + lessonId);
			HttpServerResponse res = context.response();
			
			String username = context.get("user");
			// TODO
			
			res.setStatusCode(200);
			res.end();
		});
		
		router.routeWithRegex(HttpMethod.GET, "\\/:(?<lessonId>[^\\/]\\d+)\\/comments").handler(context -> {
			String lessonId = context.request().getParam("lessonId");
			System.out.println("POST /lessons comments - lessonId " + lessonId);
			HttpServerResponse res = context.response();
			
			String username = context.get("user");
			// TODO
			
			res.setStatusCode(200);
			res.end();
		});

		return router;

	}

}
