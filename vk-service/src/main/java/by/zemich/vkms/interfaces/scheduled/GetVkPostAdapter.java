package by.zemich.vkms.interfaces.scheduled;

import by.zemich.vkms.application.internal.usecases.VkPostManagementUseCase;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class GetVkPostAdapter {
    private final VkPostManagementUseCase getVkPostUseCase;

    public GetVkPostAdapter(VkPostManagementUseCase getVkPostUseCase) {
        this.getVkPostUseCase = getVkPostUseCase;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void fetchPosts() {
        getVkPostUseCase.fetchPostsFromVK();
    }
}
