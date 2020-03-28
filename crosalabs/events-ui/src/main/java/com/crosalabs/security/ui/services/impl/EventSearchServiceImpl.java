package com.crosalabs.security.ui.services.impl;

import com.crosalabs.security.ui.model.Event;
import com.crosalabs.security.ui.repositories.EventsRepository;
import com.crosalabs.security.ui.services.EventSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class EventSearchServiceImpl implements EventSearchService {
    private final RestHighLevelClient client;
    private final ObjectMapper mapper;
    private final EventsRepository repository;

    private static final String INDEX = "events";
    private static final String TYPE = "event";

    public EventSearchServiceImpl(RestHighLevelClient client, ObjectMapper mapper, EventsRepository repository) {
        this.client = client;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Page<Event> fetchEvents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Event> fetchEvent(UUID eventId) {
        return repository.findById(eventId);
    }
}
