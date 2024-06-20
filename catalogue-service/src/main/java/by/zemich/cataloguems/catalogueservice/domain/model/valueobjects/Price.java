package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class Price {
    private Integer currencyCode;
    private BigDecimal forPiece;
    private BigDecimal forSet;

    public Price(Integer currencyCode, BigDecimal forPiece, BigDecimal forSet) {
        this.currencyCode = currencyCode;
        this.forPiece = forPiece;
        this.forSet = forSet;
    }

    public Price() {
    }

}
