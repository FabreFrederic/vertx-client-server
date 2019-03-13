package io.fabre.frederic.agent;

import io.fabre.frederic.agent.business.Ping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.shareddata.LocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Agent2Verticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(Agent2Verticle.class);

    @Override
    public void start(Future<Void> fut) {
        LOGGER.info("verticle is started");
        LocalMap<String, Ping> myMap = vertx.sharedData().getLocalMap("my-map");

        vertx.setPeriodic(1000, id -> {
            myMap.forEach((s, ping) -> LOGGER.info(Long.toString(ping.getId())));
        });
    }
}
