package by.zemich.cataloguems.catalogueservice.application.usecases;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;

import java.util.List;

public interface ProductViewUseCase {
    List<Product> findAll();
}
