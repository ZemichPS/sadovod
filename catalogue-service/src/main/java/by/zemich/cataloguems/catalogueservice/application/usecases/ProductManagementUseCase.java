package by.zemich.cataloguems.catalogueservice.application.usecases;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductManagementUseCase {
    Product create(UUID supplierUuid,
                   String supplierName,
                   UUID postId,
                   List<String> imageLinks,
                   String postText
    );
}
