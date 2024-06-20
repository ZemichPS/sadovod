package by.zemich.cataloguems.catalogueservice.infrastrucrure.services;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AIOutputAdapter implements ProductManagementAIOutputPort {

    private final AiServiceOpenFeign aiServiceOpenFeign;
    private final ObjectMapper objectMapper;

    public AIOutputAdapter(AiServiceOpenFeign aiServiceOpenFeign,
                           ObjectMapper objectMapper) {
        this.aiServiceOpenFeign = aiServiceOpenFeign;
        this.objectMapper = objectMapper;
    }


    @Override
    public ProductDto recognize(String image, String sourceText) {
        AiServiceOpenFeign.GetProductDescriptionRequest request = new AiServiceOpenFeign.GetProductDescriptionRequest(image, sourceText);
        AiServiceOpenFeign.GetProductDescriptionResponse recognizedResponse = aiServiceOpenFeign.recognize(request);

        String jsonDescription = recognizedResponse.getJsonDescription();

        try {
            return objectMapper.readValue(jsonDescription, ProductDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
