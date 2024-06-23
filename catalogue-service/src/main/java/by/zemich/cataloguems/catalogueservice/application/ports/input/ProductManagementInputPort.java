package by.zemich.cataloguems.catalogueservice.application.ports.input;

import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementFindCategoryOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDto;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Category;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.*;
import by.zemich.cataloguems.catalogueservice.domain.services.ImageService;
import by.zemich.cataloguems.catalogueservice.domain.services.ProductService;


public class ProductManagementInputPort implements ProductManagementUseCase {
    private final ProductManagementAIOutputPort aiPort;
    private final ProductManagementFindCategoryOutputPort findCategoryOutputPort;
    private final ProductManagementRepositoryOutputPort repositoryOutputPort;

    public ProductManagementInputPort(ProductManagementAIOutputPort aiPort,
                                      ProductManagementFindCategoryOutputPort findCategoryOutputPort,
                                      ProductManagementRepositoryOutputPort repositoryOutputPort) {
        this.aiPort = aiPort;
        this.findCategoryOutputPort = findCategoryOutputPort;
        this.repositoryOutputPort = repositoryOutputPort;
    }

    @Override
    public Product create(ProductId productId,
                          Supplier supplier,
                          Photos photos,
                          Link link,
                          String sourceText
    ) {
        ProductDto defaultProductDto = ProductDto.getDefault();
        String image = ImageService.getFromDtoStructure(defaultProductDto);
        ProductDto productDto = aiPort.recognize(image, sourceText);

        CategoryType categoryType = CategoryType.valueOf(productDto.getSubCategory().toUpperCase());
        Category subCategory = findCategoryOutputPort.findOrGetDefaultCategory(categoryType);

        ProductName productName = ProductService.getProductNameOf(productDto);
        Description description = ProductService.getDescriptionOf(productDto);
        Price price = ProductService.getPriceOf(productDto);

        Product newProduct = new Product(productId);
        newProduct.addSupplierId(supplier);
        newProduct.addProductName(productName);
        newProduct.addDescription(description);
        newProduct.addPhotos(photos);
        newProduct.addPrice(price);
        newProduct.addLink(link);

        //TODO нужно заменить
        newProduct.addCategory(subCategory);

        return repositoryOutputPort.persist(newProduct);
    }


}
