package by.zemich.cataloguems.catalogueservice.application.ports.output;


import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDescription;

public interface ProductManagementAIOutputPort {
    ProductDescription proceed(String sourceText);
}
