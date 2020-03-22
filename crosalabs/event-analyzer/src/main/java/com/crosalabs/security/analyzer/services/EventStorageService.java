package com.crosalabs.security.analyzer.services;

import com.crosalabs.security.analyzer.model.Event;

public interface EventStorageService {
    void save(Event event) throws Exception;
}
