package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "app", name = "images")
public class ImageEntity {
    @Id
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_uuid", referencedColumnName = "uuid")
    private ProductEntity product;

    private String link;


}
