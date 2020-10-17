package io.vertx.sample;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

public class MyFirstRXVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		HttpServer server = vertx.createHttpServer();
		server.requestStream().toObservable()
				.subscribe(r -> r.response().end("RX hello " + Thread.currentThread().getName() + "\n"));
		server.rxListen(8081).subscribe();
	}

}
