package by.zemich.parserservice.dao.entity;

import by.zemich.parserservice.core.enums.SupplierType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class SupplierEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid;

    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Version
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "name")
    private String name;
    @Column(name = "vk_id")
    private String vkId;
    @Column(name = "vk_link")
    private String vkLink;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "supplier_type")
    @Enumerated(EnumType.STRING)
    private SupplierType supplierType;
    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {
                    //CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "user_uuid")
    private UserEntity user;

    public SupplierEntity(UUID uuid,
                          Timestamp createdAt,
                          Timestamp updatedAt,
                          String name,
                          String vkId,
                          String vkLink,
                          String address,
                          String phone,
                          SupplierType supplierType) {
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.vkId = vkId;
        this.vkLink = vkLink;
        this.address = address;
        this.phone = phone;
        this.supplierType = supplierType;

    }

    public SupplierEntity() {
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

    public SupplierType getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(SupplierType ESupplierType) {
        this.supplierType = ESupplierType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierEntity that = (SupplierEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(name, that.name) && Objects.equals(vkId, that.vkId) && Objects.equals(vkLink, that.vkLink) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && supplierType == that.supplierType && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, createdAt, updatedAt, name, vkId, vkLink, address, phone, supplierType, user);
    }
}
