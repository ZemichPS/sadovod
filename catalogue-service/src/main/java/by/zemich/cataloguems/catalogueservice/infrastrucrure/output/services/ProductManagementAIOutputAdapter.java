package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.services;

import by.zemich.cataloguems.catalogueservice.application.ports.output.ProductManagementAIOutputPort;
import org.springframework.stereotype.Service;

public class ProductManagementAIOutputAdapter implements ProductManagementAIOutputPort {

    private final AiServiceOpenFeign aiServiceOpenFeign;

    public ProductManagementAIOutputAdapter(AiServiceOpenFeign aiServiceOpenFeign) {
        this.aiServiceOpenFeign = aiServiceOpenFeign;
    }

    @Override
    public String proceed(String sourceText) {
        StringBuilder builder = new StringBuilder("Extract product details from the following text:")
                .append("\n\n")
                .append(sourceText)
                .append("\n")
                .append("Details should include: name, price, type, brand, model, available in pieces, available in bulk. Format key:value;");
        return aiServiceOpenFeign.proceed(sourceText);
    }


}
