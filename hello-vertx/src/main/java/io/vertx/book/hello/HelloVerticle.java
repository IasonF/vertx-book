package io.vertx.book.hello;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer()
            .requestHandler(req -> {
                req.response().end("Hello from Vert.x. And again....and again...");
            })
            .listen(8080);
    }
    
    public static void main(String[] args) {
		System.out.println("Hello");
	}

}
