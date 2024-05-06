package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;


@Entity
public class Category {
    @Id
    @Enumerated(EnumType.STRING)
    private CategoryType type;
}

enum CategoryType {
    TSHIRT, DRESS, SHIRT
}

