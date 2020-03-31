package events.procesor;

import io.micronaut.runtime.Micronaut;

public class EventProcessor {

    public static void main(String[] args) {
        Micronaut.run(EventProcessor.class);
    }
}