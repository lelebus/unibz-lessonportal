package it.unibz.lessonPortal.restAPI.services;

import java.util.LinkedList;
import java.util.List;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import it.unibz.lessonportal.core.Comment;
import it.unibz.lessonportal.core.LearnedLesson;
import it.unibz.lessonportal.core.Lesson;
import it.unibz.lessonportal.core.exceptions.UnauthorizedException;

import org.json.JSONArray;
import org.json.JSONObject;

public class LessonEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);

		router.route().handler(context -> {
			System.out.println("lessons");

			context.next();
		});

		router.route(HttpMethod.GET, "/").handler(context -> {
			System.out.println("GET /lessons");
			HttpServerResponse res = context.response();

			String username = context.get("user");
			if (username == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			List<LearnedLesson> lessons = Lesson.Query.getLessons(username);
			JSONArray lessonArray = new JSONArray();
			for (LearnedLesson lesson : lessons) {
				JSONObject lessonObj = new JSONObject(lesson);
				if (lesson.getUsername() != null) {
					lessonObj.put("complete", true);
				}

				lessonArray.put(lessonObj);
			}

			res.setStatusCode(200);
			res.putHeader("Content-Type", "application/json");
			res.end(lessonArray.toString());
		});

		router.routeWithRegex(HttpMethod.GET, "\\/(?<lessonId>\\S+)").handler(context -> {
			String lessonId = context.request().getParam("lessonId");
			System.out.println("GET /lessons by id " + lessonId);
			HttpServerResponse res = context.response();

			String username = context.get("user");
			if (username == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			LearnedLesson lesson = Lesson.Query.getLesson(Integer.parseInt(lessonId), username);

			res.setStatusCode(200);
			res.putHeader("Content-Type", "application/json");
			res.end(new JSONObject(lesson).toString());
		});

		router.route(HttpMethod.POST, "/").handler(context -> {
			System.out.println("POST /lessons");
			HttpServerResponse res = context.response();

			String username = context.get("user");
			if (username == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			String lessonTitle = context.getBodyAsJson().getString("title");
			String lessonDescription = context.getBodyAsJson().getString("description");
			Lesson lesson = new Lesson(lessonTitle, lessonDescription);

			boolean success = lesson.new Mutation().setNewLesson(username);
			if (success) {
				res.setStatusCode(200);
				res.putHeader("Content-Type", "application/json");
				res.end(new JSONObject(lesson).toString());
			} else {
				res.setStatusCode(500);
				res.end();
			}
		});

		router.routeWithRegex(HttpMethod.POST, "\\/(?<lessonId>\\d+)").handler(context -> {
			System.out.println("/lessons leaned");
			String lessonId = context.request().getParam("lessonId");
			System.out.println("POST /lessons as learned- lessonId" + lessonId);
			HttpServerResponse res = context.response();

			String username = context.get("user");
			if (username == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			Lesson lesson = new Lesson(Integer.parseInt(lessonId));
			boolean success = lesson.new Mutation().setLessonComplete(username);
			if (success) {
				res.setStatusCode(200);
			} else {
				res.setStatusCode(500);
			}

			res.end();

		});

		router.routeWithRegex(HttpMethod.PUT, "\\/(?<lessonId>\\d+)").handler(context -> {
			String lessonId = context.request().getParam("lessonId");
			System.out.println("PUT /lessons rating - lessonId" + lessonId);
			HttpServerResponse res = context.response();

			String username = context.get("user");
			if (username == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			LearnedLesson ll = new LearnedLesson(Integer.parseInt(lessonId), username);
			boolean success = ll.new Mutation().setRating(context.getBodyAsJson().getString("rating"));

			if (success) {
				res.setStatusCode(200);
			} else {
				res.setStatusCode(500);
			}

			res.end();
		});

		router.routeWithRegex(HttpMethod.POST, "\\/(?<lessonId>\\d+)\\/comments").handler(context -> {
			String lessonId = context.request().getParam("lessonId");
			System.out.println("POST /lessons comments - lessonId " + lessonId);
			HttpServerResponse res = context.response();

			String username = context.get("user");
			if (username == null) {
				res.setStatusCode(401);
				res.end();
				return;
			}

			LearnedLesson ll = new LearnedLesson(Integer.parseInt(lessonId), username);
			LinkedList<Comment> comments = null;
			try {
				comments = ll.new Mutation().setComment(context.getBodyAsJson().getString("message"));
			} catch (UnauthorizedException e) {
				res.setStatusCode(403);
				res.end(e.toString());
			}
			System.out.println(comments.toString());
			if (comments != null) {
				res.setStatusCode(200);
				res.putHeader("Content-Type", "application/json");
				res.end(new JSONArray(comments).toString());
			}

			res.setStatusCode(500);
			res.end();
		});

		return router;

	}

}
