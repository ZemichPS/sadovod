package by.zemich.cataloguems.catalogueservice.application.ports.input;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.factory.ProductFactory;
import by.zemich.cataloguems.catalogueservice.domain.policy.parse.SimpleParsePolicy;
import by.zemich.cataloguems.catalogueservice.domain.policy.parse.shared.ParsePolicy;
import by.zemich.cataloguems.catalogueservice.domain.service.PostTextService;

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
        ParsePolicy parsePolicy = new SimpleParsePolicy();
        Map<String, Object> parsedMap = parsePolicy.parse(aiResponse);

        Product product = ProductFactory.get(
                supplierUuid,
                supplierName,
                postId,
                imageLinks,
                postText,
                parsedMap
        );
        repositoryOutputPort.persist(product);

        return product;
    }
}
