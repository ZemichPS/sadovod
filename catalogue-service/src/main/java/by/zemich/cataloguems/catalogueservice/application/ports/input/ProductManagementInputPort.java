package by.zemich.cataloguems.catalogueservice.application.ports.input;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.factory.ProductFactory;
import by.zemich.cataloguems.catalogueservice.domain.service.AiResponseToMapParserService;
import by.zemich.cataloguems.catalogueservice.domain.service.PostTextService;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.ProductCharacteristicParserService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProductManagementInputPort implements ProductManagementUseCase {

    private final ProductManagementAIOutputPort aiOutputPort;
    private final ProductManagementRepositoryOutputPort repositoryOutputPort;

    public ProductManagementInputPort(ProductManagementAIOutputPort aiOutputPort,
                                      ProductManagementRepositoryOutputPort repositoryOutputPort) {
        this.aiOutputPort = aiOutputPort;
        this.repositoryOutputPort = repositoryOutputPort;
    }

    @Override
    public Product create(UUID supplierUuid,
                          String supplierName,
                          UUID postId,
                          List<String> imageLinks,
                          String postText) {


        String textWithoutEmojis = PostTextService.removeEmojis(postText);
        String aiResponse = aiOutputPort.proceed(textWithoutEmojis);
        AiResponseToMapParserService parsePolicy = new AiResponseToMapParserService();
        Map<String, Object> parsedMap = parsePolicy.parse(aiResponse);

        Product product = ProductFactory.get(
                supplierUuid,
                supplierName,
                postId,
                imageLinks
        );
        ProductCharacteristicParserService parserService = new ProductCharacteristicParserService();
        parserService.parse(product, parsedMap);
        repositoryOutputPort.persist(product);

        return product;
    }
}
