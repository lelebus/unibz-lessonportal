package it.unibz.restAPI.services;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public interface AbstractServiceEndpoint {
	Router router(Vertx vertx);
}
