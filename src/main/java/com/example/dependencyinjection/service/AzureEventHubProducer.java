package com.example.dependencyinjection.service;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.example.dependencyinjection.config.ProducerConfig;
import com.example.dependencyinjection.config.property.AzureEventHubProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("AzureEventHub")
public class AzureEventHubProducer implements ProducerService {

    private final ProducerConfig producerConfig;

    public AzureEventHubProducer(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
    }

    @Override
    public void sendMessage() {
        AzureEventHubProperties properties = producerConfig.getAzureEventHubProperties();

        EventHubProducerClient client = new EventHubClientBuilder()
                .transportType(AmqpTransportType.AMQP)
                .connectionString(properties.getConnectionString(), properties.getName())
                .buildProducerClient();

        // sample events in an array
        List<EventData> allEvents = Arrays.asList(new EventData("Foo"), new EventData("Bar"));

        // create a batch
        EventDataBatch eventDataBatch = client.createBatch();

        for (EventData eventData : allEvents) {
            // try to add the event from the array to the batch
            if (!eventDataBatch.tryAdd(eventData)) {
                // if the batch is full, send it and then create a new batch
                client.send(eventDataBatch);
                eventDataBatch = client.createBatch();

                // Try to add that event that couldn't fit before.
                if (!eventDataBatch.tryAdd(eventData)) {
                    throw new IllegalArgumentException("Event is too large for an empty batch. Max size: "
                            + eventDataBatch.getMaxSizeInBytes());
                }
            }
        }
        // send the last batch of remaining events
        if (eventDataBatch.getCount() > 0) {
            client.send(eventDataBatch);
        }
        client.close();
    }
}
