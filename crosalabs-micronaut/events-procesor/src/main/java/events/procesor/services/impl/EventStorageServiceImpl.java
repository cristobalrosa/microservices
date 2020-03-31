package events.procesor.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.procesor.model.Event;
import events.procesor.services.EventListener;
import events.procesor.services.EventStorageService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class EventStorageServiceImpl implements EventStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventStorageServiceImpl.class);
    @Inject
    private  final RestHighLevelClient esClient;
    @Inject
    private final ObjectMapper objectMapper;

    private final String INDEX = "events";
    private final String TYPE = "event";

    public EventStorageServiceImpl(RestHighLevelClient esClient, ObjectMapper objectMapper) {
        this.esClient = esClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(Event e) throws Exception {
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, e.getId().toString())
                .source(convertProfileDocumentToMap(e), XContentType.JSON);

        IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
        String result = indexResponse.getResult().name();
        LOGGER.warn("Response from ES: " + result);
        if (!result.equalsIgnoreCase("CREATED")) {
            throw new Exception("Unable to save document.");
        }
    }

    private Map<String, Object> convertProfileDocumentToMap(Event eventDocument) {
        return objectMapper.convertValue(eventDocument, Map.class);
    }

    private Event convertMapToEventDocument(Map<String, Object> map) {
        return objectMapper.convertValue(map, Event.class);
    }
}
