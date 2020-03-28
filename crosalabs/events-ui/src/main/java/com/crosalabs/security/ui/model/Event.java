package com.crosalabs.security.ui.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

//NOTE: Deserializing the message seems to not work properly when using lombok
@Document(indexName = "events", type = "event")
public class Event {
    @Id
    private UUID id;
    private String rawData;

    public Event(){

    }

    public Event(String id, String rawData){
        this.id = UUID.fromString(id);
        this.rawData = rawData;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }
}