package by.zemich.vkms.infrastructure.config;

import by.zemich.vkms.application.internal.ports.input.VkPostManagementInputPort;
import by.zemich.vkms.application.internal.ports.output.FetchSuppliersOutputPort;
import by.zemich.vkms.application.internal.ports.output.FetchVkPostsOutputPort;
import by.zemich.vkms.application.internal.ports.output.PublishEventOutputPort;
import by.zemich.vkms.application.internal.ports.output.VkPostManagementRepositoryOutputPort;
import by.zemich.vkms.application.internal.usecases.VkPostManagementUseCase;
import by.zemich.vkms.infrastructure.output.brokers.kafka.KafkaBrokerAdapter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public KafkaBrokerAdapter kafkaBrokerAdapter(KafkaTemplate<String, byte[]> kafkaTemplate){
        return new KafkaBrokerAdapter(kafkaTemplate);
    }


    @Bean
    public VkPostManagementUseCase vkPostManagementUseCase(
            FetchSuppliersOutputPort fetchSuppliersOutputPort,
            FetchVkPostsOutputPort fetchVkPostsOutputPort,
            PublishEventOutputPort publishEventOutputPort,
            VkPostManagementRepositoryOutputPort vkPostManagementRepositoryOutputPort
    ) {
        return new VkPostManagementInputPort(
                 fetchSuppliersOutputPort,
                 fetchVkPostsOutputPort,
                 publishEventOutputPort,
                 vkPostManagementRepositoryOutputPort
        );
    }

}
