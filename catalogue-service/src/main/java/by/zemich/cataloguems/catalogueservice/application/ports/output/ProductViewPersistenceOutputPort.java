package by.zemich.cataloguems.catalogueservice.application.ports.output;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;

import java.util.List;

public interface ProductViewPersistenceOutputPort {
    List<Product> findAll();
}
