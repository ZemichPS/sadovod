package by.zemich.cataloguems.catalogueservice.domain.model.aggregates;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Category;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Description;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Price;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Supplier;
import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.util.UUID;

@Entity
@Table(name = "products", schema = "app")
public class ProductCard {
    @Id
    private UUID uuid;
    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Category category;
    @Embedded
    private Description description;
    @Embedded
    private Price price;

}
