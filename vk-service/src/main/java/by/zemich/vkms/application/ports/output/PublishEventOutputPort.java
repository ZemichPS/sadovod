package by.zemich.vkms.application.ports.output;


import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;

public interface PublishEventOutputPort {

    public void publish(VkPostCreatedEvent event);
}
