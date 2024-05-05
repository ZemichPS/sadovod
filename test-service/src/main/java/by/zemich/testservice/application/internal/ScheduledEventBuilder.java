package by.zemich.testservice.application.internal;

import by.zemich.testservice.domain.events.LenovoEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ScheduledEventBuilder {
    private final ApplicationEventPublisher eventPublisher;

    public ScheduledEventBuilder(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void publish(){
        eventPublisher.publishEvent(
                new LenovoEvent(Runtime.getRuntime().totalMemory(), "Total memory LENOVO Z50-70")
        );
    }


}


