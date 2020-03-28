package com.crosalabs.security.analyzer.model;


import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@Document(indexName = "events", type="events")
public class Event {
    private UUID id;
    private String rawData;

    public Event() {}
}
