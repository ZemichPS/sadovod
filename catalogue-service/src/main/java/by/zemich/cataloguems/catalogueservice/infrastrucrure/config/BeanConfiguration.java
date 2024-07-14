package by.zemich.cataloguems.catalogueservice.infrastrucrure.config;


import by.zemich.cataloguems.catalogueservice.application.ports.input.ProductManagementInputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.ProductManagementRepositoryOutputAdapter;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.ProductRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository.SupplierRepository;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper.ProductJpaMapper;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.services.ProductManagementAIOutputAdapter;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.services.AiServiceOpenFeign;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductManagementUseCase productManagementUseCase(ProductManagementAIOutputPort aiOutputPort,
                                                             ProductManagementRepositoryOutputPort repositoryOutputPort){
        return new ProductManagementInputPort(aiOutputPort, repositoryOutputPort);
    }

    @Bean
    public ProductManagementAIOutputPort productManagementAIOutputPort(AiServiceOpenFeign aiServiceOpenFeign) {
        return new ProductManagementAIOutputAdapter(aiServiceOpenFeign);
    }

    @Bean
    public ProductManagementRepositoryOutputPort productManagementRepositoryOutputPort(ProductRepository productRepository,
                                                                                       SupplierRepository supplierRepository,
                                                                                       ProductJpaMapper productJpaMapper) {
        return new ProductManagementRepositoryOutputAdapter(productRepository, supplierRepository, productJpaMapper);
    }

    @Bean
    public ProductJpaMapper productJpaMapper(ObjectMapper objectMapper) {
        return new ProductJpaMapper(objectMapper);
    }

}
