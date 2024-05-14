package by.zemich.cataloguems.catalogueservice.application.internal.outboundservices.alc;

import by.zemich.cataloguems.catalogueservice.domain.response.ProductDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class ExternalAiService {
    private final ObjectMapper objectMapper;
    private final ExternalAIFeignClient externalAIFeignClient;

    public ExternalAiService(ObjectMapper objectMapper,
                             ExternalAIFeignClient externalAIFeignClient) {
        this.objectMapper = objectMapper;
        this.externalAIFeignClient = externalAIFeignClient;
    }

    public ProductDescription getProductDescription(String jsonDescription, String source) {
        GetProductDescriptionRequest request = new GetProductDescriptionRequest(jsonDescription, source);
        GetProductDescriptionResponse getProductDescriptionResponse = externalAIFeignClient.getProductDescriptionResponse(request);
        return deserializeResponse(getProductDescriptionResponse.getJsonDescription());
    }

    private ProductDescription deserializeResponse(String response) {
        try {
            return objectMapper.readValue(response, ProductDescription.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

@FeignClient("AI-SERVICE")
interface ExternalAIFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "/ai-service/product_description/v1/api/")
    GetProductDescriptionResponse getProductDescriptionResponse(@RequestBody GetProductDescriptionRequest request);
}

class GetProductDescriptionResponse {
    private String jsonDescription;

    public GetProductDescriptionResponse() {
    }

    GetProductDescriptionResponse(String jsonDescription) {
        this.jsonDescription = jsonDescription;
    }

    public String getJsonDescription() {
        return jsonDescription;
    }

    public void setJsonDescription(String jsonDescription) {
        this.jsonDescription = jsonDescription;
    }
}

class GetProductDescriptionRequest {
    private String jsonDestination;
    private String source;

    public GetProductDescriptionRequest(String jsonDestination,
                                        String source) {
        this.jsonDestination = jsonDestination;
        this.source = source;
    }

    public GetProductDescriptionRequest() {
    }

    public String getJsonDestination() {
        return jsonDestination;
    }

    public void setJsonDestination(String jsonDestination) {
        this.jsonDestination = jsonDestination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}




