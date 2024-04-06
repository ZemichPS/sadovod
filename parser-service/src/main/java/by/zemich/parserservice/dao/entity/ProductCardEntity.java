package by.zemich.parserservice.dao.entity;

import by.zemich.parserservice.core.enums.EProductType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards")
public class ProductCardEntity {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private Timestamp createdAt;
    @Version
    @UpdateTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_update")
    private Timestamp updatedAt;

    @Column(name = "published_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp publishedAt;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private EProductType productType;

    @Column(name = "product_category")
    private String productCategory;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "fabric")
    private String fabric;
    @Column(name = "season")
    private String season;
    @Column(name = "fleece")
    private boolean fleece;
    @Column(name = "inSize")
    private boolean inSize;
    @Column(name = "notInSize")
    private boolean notInSize;
    @Column(name = "cost_per_set")
    private BigDecimal costPerSet;
    @Column(name = "cost_per_peace")
    private BigDecimal costPerPeace;
    @Column(name = "only_set_sale")
    private boolean onlySetSale;
    @Column(name = "sale")
    private boolean sale;
    @ElementCollection
    @OrderColumn(name = "image_list")
    private List<String> images;

    @Column(name = "release_price")
    private BigDecimal releasePrice;
    @ManyToOne(targetEntity = SupplierEntity.class,
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY)
    //@JoinColumn(name = "uuid")
    private SupplierEntity supplier;

    public ProductCardEntity() {
    }

    public ProductCardEntity(UUID uuid,
                             Timestamp updatedAt,
                             Timestamp createdAt,
                             Timestamp publishedAt,
                             EProductType productType,
                             String productCategory,
                             String color,
                             String size,
                             String fabric,
                             String season,
                             boolean fleece,
                             boolean inSize,
                             boolean notInSize,
                             BigDecimal costPerSet,
                             BigDecimal costPerPeace,
                             boolean onlySetSale,
                             boolean sale,
                             List<String> images,
                             BigDecimal releasePrice, SupplierEntity supplier) {
        this.uuid = uuid;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.productType = productType;
        this.productCategory = productCategory;
        this.color = color;
        this.size = size;
        this.fabric = fabric;
        this.season = season;
        this.fleece = fleece;
        this.inSize = inSize;
        this.notInSize = notInSize;
        this.costPerSet = costPerSet;
        this.costPerPeace = costPerPeace;
        this.onlySetSale = onlySetSale;
        this.sale = sale;
        this.images = images;
        this.releasePrice = releasePrice;
        this.supplier = supplier;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public EProductType getProductType() {
        return productType;
    }

    public void setProductType(EProductType productType) {
        this.productType = productType;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public boolean isFleece() {
        return fleece;
    }

    public void setFleece(boolean fleece) {
        this.fleece = fleece;
    }

    public boolean isInSize() {
        return inSize;
    }

    public void setInSize(boolean inSize) {
        this.inSize = inSize;
    }

    public boolean isNotInSize() {
        return notInSize;
    }

    public void setNotInSize(boolean notInSize) {
        this.notInSize = notInSize;
    }

    public BigDecimal getCostPerSet() {
        return costPerSet;
    }

    public void setCostPerSet(BigDecimal costPerSet) {
        this.costPerSet = costPerSet;
    }

    public BigDecimal getCostPerPeace() {
        return costPerPeace;
    }

    public void setCostPerPeace(BigDecimal costPerPeace) {
        this.costPerPeace = costPerPeace;
    }

    public boolean isOnlySetSale() {
        return onlySetSale;
    }

    public void setOnlySetSale(boolean onlySetSale) {
        this.onlySetSale = onlySetSale;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getReleasePrice() {
        return releasePrice;
    }

    public void setReleasePrice(BigDecimal releasePrice) {
        this.releasePrice = releasePrice;
    }
}
