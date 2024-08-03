package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka;

import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.shareddomain.events.VkPostDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaEventHandlerInputAdapter {
    private final ProductManagementUseCase productManagementUseCase;

    public KafkaEventHandlerInputAdapter(ProductManagementUseCase productManagementUseCase) {
        this.productManagementUseCase = productManagementUseCase;
    }

    @KafkaListener(topics = "vk-post-topic", id = "vk-post-service-group")
    public void handle(Message<VkPostDocument> message) {
        VkPostDocument event = message.getPayload();

        productManagementUseCase.create(
                event.getSupplierUuid(),
                event.getSupplierName(),
                event.getPostUuid(),
                event.getImageLinks(),
                event.getPostText()
        );
    }



}
