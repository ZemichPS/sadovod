package by.zemich.shareddomain.events;

public class VkPostCreatedEvent {
    private VkPostCreatedEventData vkPostCreatedEventData;

    public VkPostCreatedEvent(VkPostCreatedEventData vkPostCreatedEventData) {
        this.vkPostCreatedEventData = vkPostCreatedEventData;
    }

    public VkPostCreatedEventData getVkPostCreatedEventData() {
        return vkPostCreatedEventData;
    }

    public void setVkPostCreatedEventData(VkPostCreatedEventData vkPostCreatedEventData) {
        this.vkPostCreatedEventData = vkPostCreatedEventData;
    }
}
