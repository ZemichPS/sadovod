package by.zemich.textprocessormicroservice.domain.model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class ProductInfo {
    private String productType;
    private Material material;
    private ColorInfo colorInfo;
    private SizeInfo sizeInfo;
    private Prices prices;
    private Integer quantityInSetOrBox;

}
