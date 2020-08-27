package it.unibz.lessonPortal.restAPI.services;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class RankingEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);
		return router;
	}

}
