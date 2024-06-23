package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;

import java.util.UUID;

public record Photo(UUID uuid, Product product, String link) {
}
