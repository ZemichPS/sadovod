package by.zemich.vkms.domain.model.events;


import java.net.URL;
import java.util.UUID;

public class VkPostCreatedEvent   {

    private VkPostUuid uuid;
    private UUID supplierUuid;
    private VkPostData vkPostData;
    private URL uri;

    public VkPostCreatedEvent(VkPostUuid uuid,
                              VkPostId vkPostId,
                              UUID supplierUuid,
                              VkPostData vkPostData,
                              URL uri) {
        this.uuid = uuid;
        this.supplierUuid = supplierUuid;
        this.vkPostData = vkPostData;
        this.uri = uri;
    }

    public VkPostCreatedEvent() {
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

    public UUID getSupplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    @Override
    public String toString() {
        return "VkPostCreatedEvent{" +
                ", supplierUuid=" + supplierUuid +
                ", vkPostData=" + vkPostData +
                ", uri=" + uri +
                ", uuid=" + uuid +
                '}';
    }


};
