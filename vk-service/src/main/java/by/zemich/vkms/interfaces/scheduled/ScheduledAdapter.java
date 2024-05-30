package by.zemich.vkms.interfaces.scheduled;

import by.zemich.vkms.application.internal.usecases.ScheduledServiceUseCase;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledAdapter {
    private final ScheduledServiceUseCase scheduledServiceUseCase;

    public ScheduledAdapter(ScheduledServiceUseCase scheduledServiceUseCase) {
        this.scheduledServiceUseCase = scheduledServiceUseCase;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void fetchPosts() {
        scheduledServiceUseCase.fetchPosts();
    }
}
