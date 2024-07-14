package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(schema = "app", name = "suppliers")
public class SupplierEntity {
    @Id
    private UUID uuid;
    private String name;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "supplier"
    )
    private List<ProductEntity> productEntities;

}
