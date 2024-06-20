package by.zemich.cataloguems.catalogueservice.application.ports.output;

import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDto;

public interface ProductManagementAIOutputPort {
    ProductDto recognize(String image, String sourceText);
}
