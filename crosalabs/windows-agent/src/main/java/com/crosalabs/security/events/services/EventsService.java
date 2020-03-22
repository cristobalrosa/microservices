package com.crosalabs.security.events.services;

import com.crosalabs.security.events.model.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

    private KafkaTemplate<String, Event> kafkaTemplate;

    public EventsService(KafkaTemplate<String, Event> kafkaTemplate){
        this.kafkaTemplate  = kafkaTemplate;
    }

    public void create(){
        create(new Event());
    }

    public void create(int numberOfEvents){
        for(int i =0; i< numberOfEvents; i++) {
            create(new Event());
        }
    }

    private void create(Event analysis){
        kafkaTemplate.send("events",analysis.getId().toString(), analysis);
    }
}
