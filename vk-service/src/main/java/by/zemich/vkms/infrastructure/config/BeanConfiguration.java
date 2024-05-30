package by.zemich.vkms.infrastructure.config;

import by.zemich.vkms.application.internal.commandservices.VkPostCommandService;
import by.zemich.vkms.application.internal.ports.input.ScheduledServiceInputPort;
import by.zemich.vkms.application.internal.ports.output.SupplierClientOutputPort;
import by.zemich.vkms.application.internal.ports.output.VkClientOutputPort;
import by.zemich.vkms.application.internal.queryservices.VkPostQueryService;
import by.zemich.vkms.infrastructure.repositories.jpa.VkPostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfiguration {
    @Bean
    VkPostQueryService VkPostQueryService(VkPostRepository repository) {
        return new VkPostQueryService(repository);
    }

    @Bean
    VkPostCommandService vkPostCommandService(VkPostRepository repository) {
        return new VkPostCommandService(repository);
    }

    @Bean
    ScheduledServiceInputPort scheduledServiceInputPort(SupplierClientOutputPort supplierClientOutputPort,
                                                        VkClientOutputPort vkClientOutputPort,
                                                        VkPostCommandService vkPostCommandService
    ) {
        return new ScheduledServiceInputPort(
                supplierClientOutputPort,
                vkClientOutputPort,
                vkPostCommandService
        );
    }

}
