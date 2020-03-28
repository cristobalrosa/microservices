package com.crosalabs.security.ui.services;

import com.crosalabs.security.ui.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EventSearchService {

    Page<Event> fetchEvents(Pageable pageable);

    Optional<Event> fetchEvent(UUID eventId);
}
