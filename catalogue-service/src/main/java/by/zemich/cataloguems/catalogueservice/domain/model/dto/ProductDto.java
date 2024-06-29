package by.zemich.cataloguems.catalogueservice.domain.model.dto;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDto {
    private String productName;

    private List<String> availableColors;

    private boolean abilityToSelectColor;

    private boolean abilityToSelectPicture;

    private String availableSizes;

    boolean inSize;

    private String detailedDescription;

    private String parentCategory;

    private String subCategory;

    private BigDecimal priceForSet;

    private BigDecimal priceForPiece;

    public ProductDto() {
    }

    public ProductDto(String productName,
                      List<String> availableColors,
                      boolean abilityToSelectColor,
                      boolean abilityToSelectPicture,
                      String availableSizes,
                      boolean inSize,
                      String detailedDescription,
                      String parentCategory,
                      String subCategory,
                      BigDecimal priceForSet,
                      BigDecimal priceForPiece) {
        this.productName = productName;
        this.availableColors = availableColors;
        this.abilityToSelectColor = abilityToSelectColor;
        this.abilityToSelectPicture = abilityToSelectPicture;
        this.availableSizes = availableSizes;
        this.inSize = inSize;
        this.detailedDescription = detailedDescription;
        this.parentCategory = parentCategory;
        this.subCategory = subCategory;
        this.priceForSet = priceForSet;
        this.priceForPiece = priceForPiece;
    }


    public static ProductDto getDefault() {

        String parentCategory = Arrays.stream(CategoryType.values())
                .filter(categoryType -> !categoryType.isSubCategory())
                .map(Enum::toString)
                .collect(Collectors.joining(" or "));

        String subCategory = Arrays.stream(CategoryType.values())
                .filter(CategoryType::isSubCategory)
                .map(Enum::toString)
                .collect(Collectors.joining(" or "));

        ProductDto productDto = new ProductDto();
        productDto.setAvailableColors(new ArrayList<>());
        productDto.setParentCategory(parentCategory);
        productDto.setSubCategory(subCategory);
        return productDto;
    }
}
