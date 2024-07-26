package by.zemich.cataloguems.catalogueservice.application.ports.input;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDescription;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.factory.ProductFactory;
import by.zemich.cataloguems.catalogueservice.domain.service.PostTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class ProductManagementInputPort implements ProductManagementUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProductManagementInputPort.class);
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

       return null;

//        try {
//            Thread.sleep(14_000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        String textWithoutEmojis = PostTextService.removeEmojis(postText);
//
//        ProductDescription productDescription = aiOutputPort.proceed(textWithoutEmojis);
//
//        Product product = ProductFactory.get(
//                supplierUuid,
//                supplierName,
//                postId,
//                imageLinks,
//                productDescription
//        );
//
//        repositoryOutputPort.persist(product);
//        log.info("--- saved --- ");
//
//        return product;
    }
}
