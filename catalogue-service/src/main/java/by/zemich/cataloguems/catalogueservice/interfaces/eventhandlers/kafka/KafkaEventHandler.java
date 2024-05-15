package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka;

import by.zemich.cataloguems.catalogueservice.application.internal.commandservices.ProductCardCommandService;
import by.zemich.cataloguems.catalogueservice.domain.commands.CreateProductCardCommand;
import by.zemich.shareddomain.events.Event;
import by.zemich.shareddomain.events.VkPostCreatedEvent;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class KafkaEventHandler {
    private final ModelMapper modelMapper;
    private final ProductCardCommandService commandService;
    private final Validator validator;

    public KafkaEventHandler(ModelMapper modelMapper, ProductCardCommandService commandService, Validator validator) {
        this.modelMapper = modelMapper;
        this.commandService = commandService;
        this.validator = validator;
    }

    @KafkaListener(topics = "vk-post-topic", id = "vk-post-service-group")
    public void handle(Message<VkPostCreatedEvent> message) {
        VkPostCreatedEvent event = message.getPayload();
        validate(event);
        CreateProductCardCommand command = modelMapper.map(event, CreateProductCardCommand.class);
        commandService.create(command);
    }

    private void validate(Event event){
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
