package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Price;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.shared.ProductCharacteristicParser;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceParser implements ProductCharacteristicParser {
    private final String REGEX = "^\\d+(\\.\\d+)?$";
    Pattern pattern = Pattern.compile(REGEX);

    @Override
    public void parse(String value, Product product) {
        Matcher matcher = pattern.matcher(value);
        Price price = null;
        if (matcher.find()) {
            String parsedPrice = value.substring(matcher.start(), matcher.end());
            price = new Price(new BigDecimal(parsedPrice), 643);
        } else price = new Price(BigDecimal.ZERO, 643);

        product.addPrice(price);
    }

    @Override
    public String getKey() {
        return "price";
    }
}
