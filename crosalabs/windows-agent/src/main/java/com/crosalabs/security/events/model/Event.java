package com.crosalabs.security.events.model;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

@Data
public class Event {
    private final UUID id;
    private final String rawData;

    public Event() {
        this.id = UUID.randomUUID();
        this.rawData = RandomStringUtils.randomAlphabetic(1000);
    }
}
