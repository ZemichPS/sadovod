package by.zemich.shareddomain.events;

import java.util.UUID;

public class VkPostCreatedEventData {
    private UUID postUuid;

    public VkPostCreatedEventData(UUID postUuid) {
        this.postUuid = postUuid;
    }

    public UUID getPostUuid() {
        return postUuid;
    }

    public void setPostUuid(UUID postUuid) {
        this.postUuid = postUuid;
    }


}
