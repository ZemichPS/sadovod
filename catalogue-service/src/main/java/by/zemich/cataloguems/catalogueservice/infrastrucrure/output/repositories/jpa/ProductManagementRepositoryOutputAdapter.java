package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ProductEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.SupplierEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.ProductRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.SupplierRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.ProductJpaMapper;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.SupplierJpaMapper;

import java.util.UUID;

public class ProductManagementRepositoryOutputAdapter implements ProductManagementRepositoryOutputPort {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    private final ProductJpaMapper productJpaMapper;

    public ProductManagementRepositoryOutputAdapter(ProductRepository productRepository,
                                                    SupplierRepository supplierRepository,
                                                    ProductJpaMapper productJpaMapper) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.productJpaMapper = productJpaMapper;
    }

    @Override
    public Product persist(Product product) {
        // TODO РЕФАКТОР
        ProductEntity productEntity = productJpaMapper.productDomainToEntity(product);
        Supplier supplierFromProduct = product.getSupplier();
        UUID supplierUuid = supplierFromProduct.getId().uuid();
        SupplierEntity supplierProxy = supplierRepository.getReferenceById(supplierUuid);

        if (existsById(supplierProxy)) {
            productEntity.addSupplier(supplierProxy);
        } else productEntity.addSupplier(SupplierJpaMapper.supplierDomainToEntity(supplierFromProduct));

        productRepository.save(productEntity);
        return product;
    }

    private boolean existsById(SupplierEntity supplier) {
        try {
            supplier.getName();
            return true;
        //TODO узнать какой метод выбрасывает JPA
        } catch (Exception e) {
            return false;
        }
    }

}
