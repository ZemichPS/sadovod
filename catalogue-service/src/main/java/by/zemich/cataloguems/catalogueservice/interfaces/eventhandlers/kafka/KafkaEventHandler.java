package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka;

import by.zemich.cataloguems.catalogueservice.application.internal.commandservices.ProductCardCommandService;
import by.zemich.cataloguems.catalogueservice.domain.commands.CreateProductCardCommand;
import by.zemich.shareddomain.events.VkPostCreatedEvent;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventHandler {
    private final ModelMapper modelMapper;
    private final ProductCardCommandService commandService;

    public KafkaEventHandler(ModelMapper modelMapper, ProductCardCommandService commandService) {
        this.modelMapper = modelMapper;
        this.commandService = commandService;
    }

    @KafkaListener(topics = "vk-post-topic", id = "vk-post-service-group")
    public void handle(Message<VkPostCreatedEvent> message) {
        VkPostCreatedEvent event = message.getPayload();
        CreateProductCardCommand command = modelMapper.map(event, CreateProductCardCommand.class);
        commandService.create(command);

    }

}
