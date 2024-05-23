package by.zemich.cataloguems.catalogueservice.application.internal.commandservices;

import by.zemich.cataloguems.catalogueservice.application.internal.outboundservices.alc.ExternalAiService;
import by.zemich.cataloguems.catalogueservice.domain.commands.CreateProductCardCommand;
import by.zemich.cataloguems.catalogueservice.domain.model.aggregates.ProductCard;
import by.zemich.cataloguems.catalogueservice.domain.response.ProductDescription;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.ProductCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdurmont.emoji.EmojiParser;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public UUID create(CreateProductCardCommand command) {
        ProductCard productCard = new ProductCard(command);
        String preparedText = prepareText(command.getPostText());
        ProductDescription productDescription = externalAiService.getProductDescription(preparedText);
        productCard.addDescription(productDescription);
        repository.save(productCard);
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

    private String prepareText(String textBlock) {
        String prepared = EmojiParser.removeAllEmojis(textBlock).toLowerCase();

        return Arrays.stream(prepared.split("\n"))
                .filter(line -> !line.contains("наша группа"))
                .filter(line -> !line.contains("vk.com"))
                .filter(line -> !line.contains("whatsapp"))
                .filter(line -> !line.contains("-----"))
                .filter(line -> !line.contains("корпус"))
                .filter(line -> !line.contains("cадовод"))
                .filter(line -> !line.contains("+79"))
                .filter(line -> !line.contains("место"))
                .collect(Collectors.joining("\n "));
    }




}
