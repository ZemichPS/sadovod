package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Price {
    private Integer currencyCode;
    @Column(name = "price_for_piece")
    private BigDecimal forPiece;
    @Column(name = "price_for_set")
    private BigDecimal forSet;

    public Price(Integer currencyCode, BigDecimal forPiece, BigDecimal forSet) {
        this.currencyCode = currencyCode;
        this.forPiece = forPiece;
        this.forSet = forSet;
    }

    public Price() {
    }

    public Integer getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getForPiece() {
        return forPiece;
    }

    public void setForPiece(BigDecimal forPiece) {
        this.forPiece = forPiece;
    }

    public BigDecimal getForSet() {
        return forSet;
    }

    public void setForSet(BigDecimal forSet) {
        this.forSet = forSet;
    }
}
