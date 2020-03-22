package com.crosalabs.security.analyzer.events;

import com.crosalabs.security.analyzer.model.Event;
import com.crosalabs.security.analyzer.services.EventStorageService;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log
@Component
public class EventsListener {

    private final EventStorageService eventStorageService;
    public EventsListener(EventStorageService eventStorageService) {
        super();
        this.eventStorageService = eventStorageService;
        log.warning("Event listener registered");
    }

    @KafkaListener(topics = "events")
    public void order(Event event, Acknowledgment acknowledgment) {
        log.info("Received event " + event.getId());
        acknowledgment.acknowledge();
        try {
            this.eventStorageService.save(event);
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Unable to save the event in ES.");
            log.warning(e.getMessage());
        }
    }
}