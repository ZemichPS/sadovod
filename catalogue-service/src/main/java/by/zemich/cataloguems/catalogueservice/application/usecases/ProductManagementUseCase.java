package by.zemich.cataloguems.catalogueservice.application.usecases;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.Link;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.Photos;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.ProductId;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.Supplier;

public interface ProductManagementUseCase {
    Product create(
            ProductId productId,
            Supplier supplier,
            Photos photos,
            Link link,
            String sourceText
    );


}
