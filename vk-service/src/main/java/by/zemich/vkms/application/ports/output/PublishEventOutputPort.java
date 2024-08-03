package by.zemich.vkms.application.ports.output;


import by.zemich.vkms.domain.model.events.VkPostDocument;

public interface PublishEventOutputPort {

    public void publish(VkPostDocument event);
}
