package com.crosalabs.security.ui.repositories;

import com.crosalabs.security.ui.model.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventsRepository extends ElasticsearchRepository<Event, String> {
    Optional<Event> findById(UUID eventId);
}
