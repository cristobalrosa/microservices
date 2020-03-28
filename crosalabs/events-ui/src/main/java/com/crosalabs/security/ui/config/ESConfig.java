package com.crosalabs.security.ui.config;

import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Log
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.crosalabs.security.ui.repositories")
public class ESConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        log.warning("Creating ES client with EsHost "+ EsHost );
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(EsHost, EsPort)));

        return client;
    }
}