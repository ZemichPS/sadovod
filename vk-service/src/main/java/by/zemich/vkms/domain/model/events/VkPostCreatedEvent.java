package by.zemich.vkms.domain.model.events;


import java.net.URL;

public class VkPostCreatedEvent{
    private VkPostId vkPostId;
    private VkPostData vkPostData;
    private URL uri;
    private VkPostUuid uuid;

    public VkPostCreatedEvent(VkPostUuid uuid,
                              VkPostId vkPostId,
                              VkPostData vkPostData,
                              URL uri) {
        this.uuid = uuid;
        this.vkPostId = vkPostId;
        this.vkPostData = vkPostData;
        this.uri = uri;
    }

    public VkPostCreatedEvent() {
    }

    public VkPostId getVkPostId() {
        return vkPostId;
    }

    public void setVkPostId(VkPostId vkPostId) {
        this.vkPostId = vkPostId;
    }

    public VkPostData getVkPostData() {
        return vkPostData;
    }

    public void setVkPostData(VkPostData vkPostData) {
        this.vkPostData = vkPostData;
    }

    public URL getUri() {
        return uri;
    }

    public void setUri(URL uri) {
        this.uri = uri;
    }

    public VkPostUuid getUuid() {
        return uuid;
    }

    public void setUuid(VkPostUuid uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "VkPostCreatedEvent{" +
                "vkPostId=" + vkPostId +
                ", vkPostData=" + vkPostData +
                ", uri=" + uri +
                ", uuid=" + uuid +
                '}';
    }
};
