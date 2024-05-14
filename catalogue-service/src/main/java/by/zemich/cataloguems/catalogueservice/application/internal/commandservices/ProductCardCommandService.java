package by.zemich.cataloguems.catalogueservice.application.internal.commandservices;

import by.zemich.cataloguems.catalogueservice.application.internal.outboundservices.alc.ExternalAiService;
import by.zemich.cataloguems.catalogueservice.domain.commands.CreateProductCardCommand;
import by.zemich.cataloguems.catalogueservice.domain.model.aggregates.ProductCard;
import by.zemich.cataloguems.catalogueservice.domain.response.ProductDescription;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.ProductCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class ProductCardCommandService {
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final ProductCardRepository repository;
    private final ExternalAiService externalAiService;

    public ProductCardCommandService(ObjectMapper objectMapper,
                                     Validator validator,
                                     ProductCardRepository repository,
                                     ExternalAiService externalAiService) {
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.repository = repository;
        this.externalAiService = externalAiService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UUID create(CreateProductCardCommand command){
        validateCommand(command);
        String jsonTemplate = getJsonStringTemplate(ProductDescription.getEmptyProductInfo());
        ProductCard productCard = new ProductCard(command);
        ProductDescription productDescription = externalAiService.getProductDescription(jsonTemplate, command.getPostText());
        productCard.addDescription(productDescription);
        //repository.save(productCard);
        log.info(productCard.toString());
        return productCard.getUuid();
    }

    private String getJsonStringTemplate(ProductDescription template) {
        try {
            return objectMapper.writeValueAsString(template);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateCommand(CreateProductCardCommand command){
        Set<ConstraintViolation<CreateProductCardCommand>> violations = validator.validate(command);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<CreateProductCardCommand> violation : violations) {
                errors.append(violation.getMessage()).append("\n");
            }
            log.error(errors.toString());
            throw new ConstraintViolationException("Event validation failed: " + errors.toString(), violations);
        }
    }


}
