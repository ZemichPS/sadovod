package by.zemich.cataloguems.catalogueservice.application.ports.input;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductViewPersistenceOutputPort;
import by.zemich.cataloguems.catalogueservice.application.usecases.ProductViewUseCase;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.ProductRepository;

import java.util.List;

public class ProductViewInputPort implements ProductViewUseCase {

    private final ProductViewPersistenceOutputPort productViewOutputPort;

    public ProductViewInputPort(ProductViewPersistenceOutputPort productViewOutputPort) {
        this.productViewOutputPort = productViewOutputPort;
    }

    @Override
    public List<Product> findAll() {
        return productViewOutputPort.findAll();
    }
}
