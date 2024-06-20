package by.zemich.cataloguems.catalogueservice.application.ports.output;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;

public interface ProductManagementRepositoryOutputPort {
    Product persist(Product product);
}
