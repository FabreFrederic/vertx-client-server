package io.fabre.frederic.agent;

import io.fabre.frederic.agent.business.Ping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.shareddata.LocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class AgentVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentVerticle.class);
    private static LocalMap<String, Ping> myMap;

    public void start(Future<Void> fut) {
        LOGGER.info("verticle is started");
        myMap = vertx.sharedData().getLocalMap("my-map");

        vertx.setPeriodic(1000, id -> {
            myMap.put(UUID.randomUUID().toString(), new Ping(id, LocalDateTime.now()));
        });

    }

}
