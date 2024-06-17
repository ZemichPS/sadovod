package by.zemich.vkms.domain.model.valueobjects;

import java.util.UUID;

public class Id {
    private UUID uuid;

    public Id(UUID uuid) {
        this.uuid = uuid;
    }

    public Id() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
