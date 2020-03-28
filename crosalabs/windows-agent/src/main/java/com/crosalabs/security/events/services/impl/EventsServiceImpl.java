package com.crosalabs.security.events.services.impl;

import com.crosalabs.security.events.model.Event;
import com.crosalabs.security.events.services.EventsService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventsServiceImpl implements EventsService {

    private KafkaTemplate<String, Event> kafkaTemplate;

    public EventsServiceImpl(KafkaTemplate<String, Event> kafkaTemplate){
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