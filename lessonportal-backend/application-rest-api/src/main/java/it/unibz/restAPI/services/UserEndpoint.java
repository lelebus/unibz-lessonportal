package it.unibz.restAPI.services;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class UserEndpoint implements AbstractServiceEndpoint {

	@Override
	public Router router(Vertx vertx) {
		Router router = Router.router(vertx);
		return router;
	}

}
