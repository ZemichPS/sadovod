package by.zemich.supplierservice.infrastructure.adapters.output.postgresql.entity;



import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "suppliers")
public class SupplierEntity {
    @Id
    private UUID uuid;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
    private String name;
    private String vkId;
    private String vkLink;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    public enum Specialization{
        DRESS, OTHER, WOMEN_CLOTHES
    }

    public SupplierEntity() {
    }

    public SupplierEntity(UUID uuid,
                          Timestamp createdAt,
                          Timestamp updatedAt,
                          String name,
                          String vkId,
                          String vkLink,
                          String address,
                          String phone,
                          Specialization specialization) {
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.vkId = vkId;
        this.vkLink = vkLink;
        this.address = address;
        this.phone = phone;
        this.specialization = specialization;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public String getVkLink() {
        return vkLink;
    }

    public void setVkLink(String vkLink) {
        this.vkLink = vkLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
