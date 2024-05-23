package by.zemich.cataloguems.catalogueservice.application.internal.outboundservices.alc;

import by.zemich.cataloguems.catalogueservice.domain.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ExternalAiService {
    private final ObjectMapper objectMapper;
    private final ExternalAIFeignClient externalAIFeignClient;

    public ExternalAiService(ObjectMapper objectMapper,
                             ExternalAIFeignClient externalAIFeignClient) {
        this.objectMapper = objectMapper;
        this.externalAIFeignClient = externalAIFeignClient;
    }

    public ProductDescription getProductDescription(String source) {

        String fields = Arrays.stream(SimpleProductDescription.class.getDeclaredFields())
                .map(field -> {
                    String[] types = field.getType().getTypeName().split("\\.");
                    String type = types[types.length - 1];
                    String name = field.getName();
                    return type + " " + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
                }).collect(Collectors.joining("\n"));


        String classDescriptor = """
                public class ProductDescription{
                %s
                }
                """.formatted(fields);

        GetProductDescriptionRequest request = new GetProductDescriptionRequest(classDescriptor, source);
        GetProductDescriptionResponse getProductDescriptionResponse = tryGetProductDescription(request);
        SimpleProductDescription simpleProductDescription = deserializeResponse(getProductDescriptionResponse.getJsonDescription());
        ProductDescription productDescription = mapSimpleProductDescriptionToProductDescription(simpleProductDescription);
        return productDescription;
    }

    private SimpleProductDescription deserializeResponse(String response) {
        try {
            response = response.replace("`", "");
            response = response.replace("json", "");
            return objectMapper.readValue(response, SimpleProductDescription.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private GetProductDescriptionResponse tryGetProductDescription(GetProductDescriptionRequest request) {
        int tryCounter = 0;
        String exceptionMessage = "";

        while (tryCounter <=2) {
            try {
                return externalAIFeignClient.getProductDescriptionResponse(request);
            } catch (Exception exception) {
                exceptionMessage = exception.getMessage();
                log.error(exceptionMessage);
                tryCounter++;
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException(exceptionMessage);
    }

    private ProductDescription mapSimpleProductDescriptionToProductDescription(SimpleProductDescription simplepd) {

        ColorInfo colorInfo = new ColorInfo();
        colorInfo.setColorlist(simplepd.getColorList());
        colorInfo.setPossibleToChooseColor(simplepd.isAbilityToChooseColor());
        colorInfo.setPossibleToChoosePicture(simplepd.isAbilityToChoosePicture());

        SizeInfo sizeInfo = new SizeInfo();
        sizeInfo.setSizeList(simplepd.getSizeList());
        sizeInfo.setInSize(simplepd.isInSize());
        String range = simplepd.getSizeList().size() == 1 ? simplepd.getSizeList().get(0) : "";
        sizeInfo.setSizeRange(range);

        return ProductDescription.builder()
                .withProductName(simplepd.getProductName())
                .withColorInfo(colorInfo)
                .withMaterial(new Material(simplepd.getMaterialType(), simplepd.getQuality()))
                .withPrices(new Prices(simplepd.costPerPiece, simplepd.costPerSetOrBox))
                .withSizeInfo(sizeInfo)
                .withQuantityInSetOrBox(simplepd.getQuantityInSetOrBox())
                .withSale(simplepd.isSale())
                .build();
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

class SimpleProductDescription {
    private String productName;
    private String materialType;
    private String quality;
    private List<String> colorList;
    private boolean abilityToChooseColor;
    private boolean abilityToChoosePicture;
    private List<String> sizeList;
    private boolean inSize;
    Double costPerPiece;
    Double costPerSetOrBox;
    private Integer quantityInSetOrBox;
    private boolean sale;
    private String correctedFullProductDescription;

    SimpleProductDescription(String productName, String materialType, String quality, List<String> colorList, boolean abilityToChooseColor, boolean abilityToChoosePicture, List<String> sizeList, boolean inSize, Double costPerPiece, Double costPerSetOrBox, Integer quantityInSetOrBox, boolean sale, String correctedFullProductDescription) {
        this.productName = productName;
        this.materialType = materialType;
        this.quality = quality;
        this.colorList = colorList;
        this.abilityToChooseColor = abilityToChooseColor;
        this.abilityToChoosePicture = abilityToChoosePicture;
        this.sizeList = sizeList;
        this.inSize = inSize;
        this.costPerPiece = costPerPiece;
        this.costPerSetOrBox = costPerSetOrBox;
        this.quantityInSetOrBox = quantityInSetOrBox;
        this.sale = sale;
        this.correctedFullProductDescription = correctedFullProductDescription;
    }

    public SimpleProductDescription() {
    }

    public String getProductName() {
        return this.productName;
    }

    public String getMaterialType() {
        return this.materialType;
    }

    public String getQuality() {
        return this.quality;
    }

    public List<String> getColorList() {
        return this.colorList;
    }

    public boolean isAbilityToChooseColor() {
        return this.abilityToChooseColor;
    }

    public boolean isAbilityToChoosePicture() {
        return this.abilityToChoosePicture;
    }

    public List<String> getSizeList() {
        return this.sizeList;
    }

    public boolean isInSize() {
        return this.inSize;
    }

    public Double getCostPerPiece() {
        return this.costPerPiece;
    }

    public Double getCostPerSetOrBox() {
        return this.costPerSetOrBox;
    }

    public Integer getQuantityInSetOrBox() {
        return this.quantityInSetOrBox;
    }

    public boolean isSale() {
        return this.sale;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public void setAbilityToChooseColor(boolean abilityToChooseColor) {
        this.abilityToChooseColor = abilityToChooseColor;
    }

    public void setAbilityToChoosePicture(boolean abilityToChoosePicture) {
        this.abilityToChoosePicture = abilityToChoosePicture;
    }

    public void setSizeList(List<String> sizeList) {
        this.sizeList = sizeList;
    }

    public void setInSize(boolean inSize) {
        this.inSize = inSize;
    }

    public void setCostPerPiece(Double costPerPiece) {
        this.costPerPiece = costPerPiece;
    }

    public void setCostPerSetOrBox(Double costPerSetOrBox) {
        this.costPerSetOrBox = costPerSetOrBox;
    }

    public void setQuantityInSetOrBox(Integer quantityInSetOrBox) {
        this.quantityInSetOrBox = quantityInSetOrBox;
    }

    public String getCorrectedFullProductDescription() {
        return correctedFullProductDescription;
    }

    public void setCorrectedFullProductDescription(String correctedFullProductDescription) {
        this.correctedFullProductDescription = correctedFullProductDescription;
    }

    public void setSale(boolean sale) {
        this.sale = sale;


    }
}


