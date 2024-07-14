package by.zemich.cataloguems.catalogueservice.domain.model.valueobject;

import java.math.BigDecimal;

public record Price(BigDecimal originPrice, int ISO4217CurrencyCode) {
}
