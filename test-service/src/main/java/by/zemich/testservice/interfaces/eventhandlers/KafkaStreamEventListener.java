package by.zemich.testservice.interfaces.eventhandlers;

import by.zemich.testservice.interfaces.eventhandlers.event.TestEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Log4j2
@EnableBinding(Sink.class)
public class KafkaStreamEventListener {
    @StreamListener(Sink.INPUT)
    public void handleEvent(TestEvent event){
       log.info(event);
    }
}
