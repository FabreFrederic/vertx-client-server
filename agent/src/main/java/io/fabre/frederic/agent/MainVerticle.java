package io.fabre.frederic.agent;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start() {
        LOGGER.info("Main verticle is starting");
        vertx.deployVerticle(AgentVerticle.class.getName());
        vertx.deployVerticle(Agent2Verticle.class.getName());
    }
}
