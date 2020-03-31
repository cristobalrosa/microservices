package events.procesor.services;

import events.procesor.model.Event;

public interface EventStorageService {
    void save(Event e) throws Exception;
}
