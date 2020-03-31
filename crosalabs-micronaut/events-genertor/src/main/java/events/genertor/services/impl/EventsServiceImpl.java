package events.genertor.services.impl;

import events.genertor.client.EventsKafkaClient;
import events.genertor.model.Event;
import events.genertor.services.EventsService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventsServiceImpl implements EventsService {

    @Inject
    private final EventsKafkaClient kafkaClient;

    public EventsServiceImpl(EventsKafkaClient kafkaClient) {
        this.kafkaClient = kafkaClient;
    }


    @Override
    public void generate() {
        generate(1);
    }

    @Override
    public void generate(int numberOfEvents) {
        for(int i=0; i< numberOfEvents; i++){
            Event e = new Event();
            kafkaClient.send(e.getId().toString(),e );
        }
    }
}
