package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity;

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
@Table(name = "categories")
public class CategoryEntity {
    @Id
    private UUID uuid;

    private String categoryType;

    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductEntity> productEntities;

    @ManyToOne(
            fetch = FetchType.LAZY
            //TODO ????
    )
    @JoinColumn(name = "parrent_category_uuid",  referencedColumnName = "uuid")
    private CategoryEntity parentCategory;

    @OneToMany(
            mappedBy = "categoryParent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CategoryEntity> subcategories;


}
