package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.domain.exception.PersistenceException;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ProductEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.SupplierEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.ProductRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.SupplierRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.ImageEntityJpaMapper;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.ProductJpaMapper;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.SupplierJpaMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public class ProductManagementRepositoryOutputAdapter implements ProductManagementRepositoryOutputPort {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;


    public ProductManagementRepositoryOutputAdapter(ProductRepository productRepository,
                                                    SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional
    public Product persist(Product product) {
        // TODO РЕФАКТОР
        Supplier supplierDomain = product.getSupplier();

        ProductEntity productEntity = null;
        try {
            productEntity = ProductJpaMapper.productDomainToEntity(product);
        } catch (JsonProcessingException e) {
            throw new PersistenceException("Failed to persist product.",e);
        }
        SupplierEntity supplierEntity;

        if (supplierRepository.existsById(supplierDomain.getId().uuid())) {
            supplierEntity = supplierRepository.getReferenceById(supplierDomain.getId().uuid());
        } else {
            SupplierEntity newSupplier = SupplierJpaMapper.supplierDomainToEntity(supplierDomain);
            newSupplier.setProductEntities(new ArrayList<>());
            supplierEntity = supplierRepository.save(newSupplier);
        }

        productEntity.addSupplier(supplierEntity);
        productEntity.getImageEntityList().clear();
        ProductEntity finalProductEntity = productEntity;

        product.getProductImageList().stream()
                .map(ImageEntityJpaMapper::imageDomainToEntity)
                .peek(image -> image.setProduct(finalProductEntity))
                .forEach(productEntity::addImage);

        productRepository.save(productEntity);
        return product;
    }
}
