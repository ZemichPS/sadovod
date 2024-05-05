package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Price {
    private String currencyCode;
    private BigDecimal price;
}
