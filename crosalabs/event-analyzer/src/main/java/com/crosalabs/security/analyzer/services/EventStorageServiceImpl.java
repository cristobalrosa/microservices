package com.crosalabs.security.analyzer.services;

import com.crosalabs.security.analyzer.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log
public class EventStorageServiceImpl implements EventStorageService {

    private RestHighLevelClient client;
    private ObjectMapper objectMapper;

    private final String INDEX = "events";
    private final String TYPE = "event";

    public EventStorageServiceImpl(RestHighLevelClient esClient, ObjectMapper mapper) {
        this.client = esClient;
        this.objectMapper = mapper;
    }

    @Override
    public void save(Event document) throws Exception {

        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, document.getId().toString())
                .source(convertProfileDocumentToMap(document), XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        String result = indexResponse.getResult().name();
        log.warning("Response from ES: " + result);
        if (!result.equalsIgnoreCase("CREATED")) {
            throw new Exception("Unable to save document.");
        }
    }

    private Map<String, Object> convertProfileDocumentToMap(Event eventDocument) {
        return objectMapper.convertValue(eventDocument, Map.class);
    }

    private Event convertMapToProfileDocument(Map<String, Object> map) {
        return objectMapper.convertValue(map, Event.class);
    }

}
