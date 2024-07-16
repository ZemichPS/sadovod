package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.QuantityInSet;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.shared.ProductCharacteristicParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantityInSetParser implements ProductCharacteristicParser {
    private final String REGEX = "^\\d+$";
    Pattern pattern = Pattern.compile(REGEX);

    @Override
    public void parse(String value, Product product) {
        Matcher matcher = pattern.matcher(value);
        QuantityInSet quantity = null;
        if (matcher.find()) {
            String parsedQuantity = value.substring(matcher.start(), matcher.end());
            quantity = new QuantityInSet(Integer.valueOf(parsedQuantity));
        } else quantity = new QuantityInSet(1);
        product.addQuantityInSet(quantity);
    }

    @Override
    public String getKey() {
        return "";
    }
}
