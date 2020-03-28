package com.crosalabs.security.ui;

import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Log
public class EventsUI {
    public static void main(String[] args) {
        SpringApplication.run(EventsUI.class, args);
    }
    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @PostConstruct
    private void init() {
        log.info("Waiting for elastic search");
        // Wait for ES.
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(EsHost, EsPort)));
        ClusterHealthRequest request = new ClusterHealthRequest();
        ClusterHealthStatus status = ClusterHealthStatus.RED;
        while (status == ClusterHealthStatus.RED) {
            try {
                TimeUnit.SECONDS.sleep(30);
                ClusterHealthResponse response = client.cluster().health(request, RequestOptions.DEFAULT);
                status = response.getStatus();
                log.warning("Cluster status " + response.getStatus().toString());
            } catch (IOException | InterruptedException e) {
                log.warning("Unable to connect to ES .... retrying in 30s");
            }
        }

    }
}