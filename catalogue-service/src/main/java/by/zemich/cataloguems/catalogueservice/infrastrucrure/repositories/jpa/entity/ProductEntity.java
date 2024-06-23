package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.Photo;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity.valueobjects.Color;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity.valueobjects.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private UUID uuid;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "author_id", referencedColumnName = "uuid")
    private CategoryEntity category;

    private UUID supplierUuid;

    private UUID supplierTitle;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PhotoEntity> photoEntities;

    @Embedded
    private Color color;

    @Embedded
    private Size size;

    private String detailedDescription;


}
