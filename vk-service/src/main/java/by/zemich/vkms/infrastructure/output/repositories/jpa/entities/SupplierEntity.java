package by.zemich.vkms.infrastructure.output.repositories.jpa.entities;

import by.zemich.vkms.domain.model.entities.Supplier;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "suppliers", schema = "app")
public class SupplierEntity {
    @Id
    private UUID uuid;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "supplier"
    )
    private List<VkPostEntity> vkPostEntityList;

    private String vkId;
    private String name;

    public SupplierEntity() {
    }

    public SupplierEntity(UUID uuid,
                          List<VkPostEntity> vkPostEntityList,
                          String vkId,
                          String name) {
        this.uuid = uuid;
        this.vkPostEntityList = vkPostEntityList;
        this.vkId = vkId;
        this.name = name;
    }

    public SupplierEntity addPost(VkPostEntity vkPost) {
        if (!vkPostEntityList.contains(vkPost)) vkPostEntityList.add(vkPost);
        return this;
    }

    public static SupplierEntity createNewSupplier(Supplier from) {
        SupplierEntity supplier = new SupplierEntity();
        supplier.setUuid(from.getUuid());
        supplier.setName(from.getName());
        supplier.setVkId(from.getVkId());
        supplier.setVkPostEntityList(new ArrayList<>());
        return supplier;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<VkPostEntity> getVkPostEntityList() {
        return vkPostEntityList;
    }

    public void setVkPostEntityList(List<VkPostEntity> vkPostEntityList) {
        this.vkPostEntityList = vkPostEntityList;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
