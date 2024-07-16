package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Name;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.shared.ProductCharacteristicParser;

public class NameParser implements ProductCharacteristicParser {
    @Override
    public void parse(String value, Product product) {
        Name name = new Name(value);
        product.addName(name);
    }

    @Override
    public String getKey() {
        return "";
    }
}
