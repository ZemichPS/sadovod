package by.zemich.cataloguems.catalogueservice.domain.model.aggregates;

import by.zemich.cataloguems.catalogueservice.domain.commands.CreateProductCardCommand;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.*;
import by.zemich.cataloguems.catalogueservice.domain.response.ProductDescription;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "products", schema = "app")
public class ProductCard {
    @Id
    private UUID uuid;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
    private String productName;
    @Embedded
    private VkPost vkPost;
    @Embedded
    private SupplierUuid supplierUuid;
    @Embedded
    private Description description;
    @Embedded
    private Price price;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_uuid")
    private List<Photo> photoList;
    private Integer quantityInSet;
    private boolean sale;
    public ProductCard() {
    }

    public ProductCard(CreateProductCardCommand command) {
        this.uuid = UUID.randomUUID();
        this.vkPost = new VkPost(
                command.getPostUuid(),
                command.getPostText(),
                command.getPublishedAt(),
                command.getPostUri());
        this.supplierUuid = new SupplierUuid(command.getPostUuid());
        this.photoList = command.getImagesLinkList().stream()
                .map(link -> new Photo(UUID.randomUUID(), this.uuid, link))
                .toList();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void addDescription(ProductDescription productDescription) {
        this.productName = productDescription.getProductName() != null ? productDescription.getProductName() : "товар";
        this.sale = productDescription.getSale() != null ? productDescription.getSale() : false;
        this.quantityInSet = productDescription.getQuantityInSetOrBox() != null ? productDescription.getQuantityInSetOrBox() : 1;


        BigDecimal priceForPiece = productDescription.getPrices().getCostPerPiece() != null ? BigDecimal.valueOf(productDescription.getPrices().getCostPerPiece()) : new BigDecimal(0);
        BigDecimal priceForSet = productDescription.getPrices().getCostPerSetOrBox() != null ? BigDecimal.valueOf(productDescription.getPrices().getCostPerSetOrBox()) : new BigDecimal(0);

        Price price = new Price();
        price.setCurrencyCode(643);
        price.setForPiece(priceForPiece);
        price.setForSet(priceForSet);
        this.price = price;

        Description description = new Description();

        ColorData colorData = new ColorData(new ArrayList<>());
        SizeData sizeData = new SizeData(new ArrayList<>());

        if (Objects.nonNull(productDescription.getColorInfo())) {
            List<String> colorList = productDescription.getColorInfo().getColorlist() != null ? productDescription.getColorInfo().getColorlist() : new ArrayList<String>();
            colorData.setColorList(colorList);
            colorData.setColorChoice(productDescription.getColorInfo().isPossibleToChooseColor());
            colorData.setPictureChoice(productDescription.getColorInfo().isPossibleToChoosePicture());
        }

        if (Objects.nonNull(productDescription.getMaterial())) {
            // TODO заменить
            if (Objects.nonNull(productDescription.getMaterial().getType()))
                description.setMaterialName(productDescription.getMaterial().getType());

            if (Objects.nonNull(productDescription.getMaterial().getQuality()))
                description.setQuality(productDescription.getMaterial().getQuality());


        }

        if (Objects.nonNull(productDescription.getSizeInfo())) {
            sizeData.setSizeRange(productDescription.getSizeInfo().getSizeRange() != null ? productDescription.getSizeInfo().getSizeRange() : "");
            sizeData.setSizeList(productDescription.getSizeInfo().getSizeList() != null ? productDescription.getSizeInfo().getSizeList() : new ArrayList<>());
            sizeData.setInSize(productDescription.getSizeInfo().isInSize());
        }

        description.setColorData(colorData);
        description.setSizeData(sizeData);

        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductCard{" +
                "uuid=" + uuid +
                ", productName='" + productName + '\'' +
                ", vkPost=" + vkPost +
                ", supplierUuid=" + supplierUuid +
                ", description=" + description +
                ", price=" + price +
                ", photoList=" + photoList +
                ", quantityInSet=" + quantityInSet +
                ", sale=" + sale +
                '}';
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
