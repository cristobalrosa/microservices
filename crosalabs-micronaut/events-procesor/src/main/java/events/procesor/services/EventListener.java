package events.procesor.services;

import events.procesor.model.Event;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@KafkaListener(groupId = "events-consumers")
public class EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

    @Inject
    private final EventStorageService eventStorageService;

    public EventListener(EventStorageService eventStorageService) {
        this.eventStorageService = eventStorageService;
    }


    @Topic("events")
    public void receive(
            @KafkaKey String key,
            Event event,
            long offset,
            int partition,
            String topic,
            long timestamp) {
        LOGGER.warn("Event received " + event.getId());
        try {
            eventStorageService.save(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
