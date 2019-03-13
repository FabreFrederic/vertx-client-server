package io.fabre.frederic.agent.business;

import io.vertx.core.shareddata.Shareable;

import java.time.LocalDateTime;

public class Ping implements Shareable {
    private long id;
    private LocalDateTime date;

    public Ping(final long id, final LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Ping setId(final int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Ping setDate(final LocalDateTime date) {
        this.date = date;
        return this;
    }
}
