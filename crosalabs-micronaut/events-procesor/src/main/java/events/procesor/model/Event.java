package events.procesor.model;

import java.util.UUID;

public class Event {
    private  UUID id;
    private String rawData;

    public Event() {
    }

    public UUID getId() {
        return id;
    }

    public String getRawData() {
        return rawData;
    }
}
