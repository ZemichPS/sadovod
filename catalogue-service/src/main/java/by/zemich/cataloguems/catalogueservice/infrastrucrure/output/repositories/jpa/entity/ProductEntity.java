package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(schema = "app", name = "products")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ProductEntity {
    @Id
    private UUID uuid;
    private UUID postUuid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_uuid", referencedColumnName = "uuid")
    private SupplierEntity supplier;
    private String type;
    private Integer ISO4217CurrencyCode;
    private BigDecimal originPrice;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "product"
    )
    private List<ImageEntity> imageEntityList;
    private boolean availableInPieces;
    private boolean availableInBulk;
    private Integer quantityInPackage;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private String attributes;

    public void addSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }


}


