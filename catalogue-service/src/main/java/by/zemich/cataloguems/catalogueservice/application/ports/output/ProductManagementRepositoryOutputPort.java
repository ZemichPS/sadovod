package by.zemich.cataloguems.catalogueservice.application.ports.output;


import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;

public interface ProductManagementRepositoryOutputPort {
    Product persist(Product product);
}
