package by.zemich.cataloguems.catalogueservice.application.internal.usecases;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.Link;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.Photos;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.ProductId;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.SupplierId;
import by.zemich.shareddomain.events.VkPostCreatedEvent;

public interface ProductManagementUseCase {
    Product create(
            ProductId productId,
            SupplierId supplierId,
            Photos photos,
            Link link,
            String sourceText
    );
}
