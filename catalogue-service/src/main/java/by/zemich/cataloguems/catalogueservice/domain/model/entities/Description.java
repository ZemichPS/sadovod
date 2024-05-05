package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Description {
    String color;
    String sizes;
    String fabric;
}
