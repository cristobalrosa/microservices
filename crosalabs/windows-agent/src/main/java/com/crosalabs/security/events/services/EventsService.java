package com.crosalabs.security.events.services;

public interface EventsService {
    /**
     * Create a random Event
     */
    void create();


    /**
     * Create numberOfEvents random Events and sends them
     * @param numberOfEvents number of events to generate
     */
    void create(int numberOfEvents);
}
