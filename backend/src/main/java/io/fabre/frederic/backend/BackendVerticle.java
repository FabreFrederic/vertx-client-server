package io.fabre.frederic.backend;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendVerticle extends AbstractVerticle {
    private static final String ADDRESS = "test.address";
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendVerticle.class);

    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);
        BridgeOptions opts = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress(ADDRESS));

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx).bridge(opts);

        router.route("/eventbus/*").handler(sockJSHandler);
        router.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router::accept).listen(8021);

        router.route().handler(io.vertx.ext.web.handler.CorsHandler.create("http://localhost:8021")
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.PUT)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowCredentials(true)
                .allowedHeader("Access-Control-Allow-Method")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Content-Type"));
        LOGGER.info("Verticle is started");
    }

}