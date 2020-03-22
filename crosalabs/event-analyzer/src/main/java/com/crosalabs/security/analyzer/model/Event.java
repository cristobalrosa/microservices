package com.crosalabs.security.analyzer.model;


import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@Document(indexName = "events", type="events")
public class Event {
    private final UUID id;
    private final String rawData;

    public Event() {
        this.id = UUID.randomUUID();
        this.rawData = RandomStringUtils.randomAlphabetic(1000);
    }
}
