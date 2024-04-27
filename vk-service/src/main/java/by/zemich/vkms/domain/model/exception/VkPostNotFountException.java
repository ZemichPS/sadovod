package by.zemich.vkms.domain.model.exception;

public class VkPostNotFountException extends RuntimeException{

    public VkPostNotFountException() {
        super("Vk post is nowhere to be found");
    }
}
