package by.zemich.cataloguems.catalogueservice.domain.response;

import lombok.*;

import java.util.ArrayList;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class ProductDescription {
    private String productName;
    private Material material;
    private ColorInfo colorInfo;
    private SizeInfo sizeInfo;
    private Prices prices;
    private Integer quantityInSetOrBox;
    private Boolean sale;

    public static ProductDescription getEmptyProductInfo() {
        return ProductDescription.builder()
                .withProductName("")
                .withColorInfo(new ColorInfo(new ArrayList<>(), true, true))
                .withMaterial(new Material("", ""))
                .withPrices(new Prices(Double.valueOf(0), Double.valueOf(0)))
                .withSizeInfo(new SizeInfo("", new ArrayList<>(), true))
                .withQuantityInSetOrBox(1)
                .withSale(true)
                .build();
    }

}


