package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka;

import by.zemich.cataloguems.catalogueservice.application.internal.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.*;
import by.zemich.shareddomain.events.Event;
import by.zemich.shareddomain.events.VkPostCreatedEvent;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@Slf4j
public class KafkaEventHandlerInputAdapter {
    private final Validator validator;
    private final ProductManagementUseCase productManagementUseCase;


    public KafkaEventHandlerInputAdapter(Validator validator,
                                         ProductManagementUseCase productManagementUseCase) {
        this.validator = validator;
        this.productManagementUseCase = productManagementUseCase;
    }

    @KafkaListener(topics = "vk-post-topic", id = "vk-post-service-group")
    public void handle(Message<VkPostCreatedEvent> message) {
        VkPostCreatedEvent event = message.getPayload();
        validate(event);

        ProductId productId = new ProductId(UUID.randomUUID());
        SupplierId supplierId = new SupplierId(event.getSupplierUuid());
        List<Photo> photoList = event.getVkPostData().imagesLinkList()
                .stream()
                .map(strLink -> new Photo(UUID.randomUUID(), productId.uuid(), strLink))
                .toList();
        Photos photos = new Photos(photoList);
        Link link = new Link(event.getUri().getPath());
        String sourceText = event.getVkPostData().text();

        productManagementUseCase.create(productId,
                supplierId,
                photos,
                link,
                sourceText
        );
    }

    private void validate(Event event) {
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<Event> violation : violations) {
                errors.append(violation.getMessage()).append("\n");
            }
            log.error(errors.toString());
            throw new ConstraintViolationException("Event validation failed: " + errors.toString(), violations);
        }
    }

}
