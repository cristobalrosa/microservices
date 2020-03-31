package events.genertor.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class Event {
    private final UUID id;
    private final String rawData;

    public Event() {
        this.id = UUID.randomUUID();
        this.rawData = RandomStringUtils.randomAlphabetic(1000);
    }

    public UUID getId() {
        return id;
    }

    public String getRawData() {
        return rawData;
    }
}
