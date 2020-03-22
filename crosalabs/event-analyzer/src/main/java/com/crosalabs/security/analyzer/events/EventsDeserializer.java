package com.crosalabs.security.analyzer.events;

import com.crosalabs.security.analyzer.model.Event;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class EventsDeserializer extends JsonDeserializer<Event> {

    public EventsDeserializer() {
        super(Event.class);
    }

}