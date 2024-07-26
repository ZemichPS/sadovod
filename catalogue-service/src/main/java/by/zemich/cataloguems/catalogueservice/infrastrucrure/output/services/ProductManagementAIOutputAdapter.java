package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.services;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import by.zemich.cataloguems.catalogueservice.domain.exception.ParsingDescriptionException;
import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


public class ProductManagementAIOutputAdapter implements ProductManagementAIOutputPort {

    private final AiServiceOpenFeign aiServiceOpenFeign;
    private final ObjectMapper objectMapper;

    public ProductManagementAIOutputAdapter(AiServiceOpenFeign aiServiceOpenFeign,
                                            ObjectMapper objectMapper) {
        this.aiServiceOpenFeign = aiServiceOpenFeign;
        this.objectMapper = objectMapper;
    }

    @Override
    public by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDescription proceed(String sourceText) {

        String responseJsonStructure = """ 
        {
            "name": "Sample Product",
            "price": 29.99,
            "quantity_in_set": 1,
            "category": "детская одежда ИЛИ мужская одежда ИЛИ женская одежда ИЛИ что угодно "
            "available_in_pieces": true,
            "available_in_bulk": false,
            "other_attributes": [
                {
                    "key": "Color",
                    "value": "Red"
                },
                {
                    "key": "Size",
                    "value": "Medium"
                }
            ]
        }
        """;

        StringBuilder builder = new StringBuilder("Extract productDto details from the following text:")
                .append("\n")
                .append(sourceText)
                .append("\n")
                .append("\n")
                .append("Response must match JSON:")
                .append("\n")
                .append(responseJsonStructure)
                .append("\n")
                .append("Correct grammatical errors.")
                .append("Set null if value doesn't exist");

        String response = aiServiceOpenFeign.proceed(builder.toString());
        String preparedResponse = prepareResponse(response);
        return getProductDescription(preparedResponse);

    }



    private String prepareResponse(String response){
        return response.toLowerCase()
                .replace("```json", "")
                .replace("```", "");
    }

    private ProductDescription getProductDescription(String jsonResponse){
        try {
            return objectMapper.readValue(jsonResponse, ProductDescription.class);
        } catch (JsonProcessingException e) {
            throw new ParsingDescriptionException("Failed to get productDto description.", e);
        }
    }
}
