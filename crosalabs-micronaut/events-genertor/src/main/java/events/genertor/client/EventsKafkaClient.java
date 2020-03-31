package events.genertor.client;

import events.genertor.model.Event;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import org.apache.kafka.clients.producer.ProducerConfig;

@KafkaClient(
        id="events-client",
        acks = KafkaClient.Acknowledge.ALL,
        properties = @Property(name = ProducerConfig.RETRIES_CONFIG, value = "5")
)
public interface EventsKafkaClient {
    @Topic("events")
    void send(@KafkaKey String key, Event event);
}
