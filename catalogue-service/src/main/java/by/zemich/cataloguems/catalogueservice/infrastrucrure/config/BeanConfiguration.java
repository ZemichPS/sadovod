package by.zemich.cataloguems.catalogueservice.infrastrucrure.config;

import by.zemich.cataloguems.catalogueservice.application.usecases.ProductManagementUseCase;
import by.zemich.cataloguems.catalogueservice.application.ports.input.ProductManagementInputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementFindCategoryOutputPort;
import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementRepositoryOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductManagementUseCase productManagementUseCase(ProductManagementAIOutputPort aiPort,
                                                             ProductManagementFindCategoryOutputPort findCategoryOutputPort,
                                                             ProductManagementRepositoryOutputPort repositoryOutputPort){
        return new ProductManagementInputPort(aiPort, findCategoryOutputPort, repositoryOutputPort);
    }
}
