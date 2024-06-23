package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "photos")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhotoEntity {

    @Id
    private UUID uuid;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "product_uuid", referencedColumnName = "uuid")
    private ProductEntity product;

    private String link;

}
