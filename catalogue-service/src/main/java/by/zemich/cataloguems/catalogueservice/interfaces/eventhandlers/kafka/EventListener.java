package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka;

import by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka.events.VkPostCreatedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class EventListener {
    @StreamListener(Sink.INPUT)
    public void handleEvent(VkPostCreatedEvent event){
        System.out.println(event);
    }

}
