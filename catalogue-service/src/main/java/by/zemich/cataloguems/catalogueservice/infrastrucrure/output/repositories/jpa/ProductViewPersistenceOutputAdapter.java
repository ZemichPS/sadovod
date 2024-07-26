package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductViewPersistenceOutputPort;
import by.zemich.cataloguems.catalogueservice.domain.exception.GettingDataException;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ProductEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.ProductRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.ProductJpaMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProductViewPersistenceOutputAdapter implements ProductViewPersistenceOutputPort {

    private final ProductRepository productRepository;

    public ProductViewPersistenceOutputAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        final List<ProductEntity> allEntities = productRepository.findAll();
        return allEntities.stream()
                .map(product -> {
                    try {
                        return ProductJpaMapper.entityToDomain(product);
                    } catch (JsonProcessingException e) {
                        throw new GettingDataException("Failed to get products.", e);
                    }
                })
                .toList();
    }
}
