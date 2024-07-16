package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Type;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.shared.ProductCharacteristicParser;

public class TypeParser implements ProductCharacteristicParser {
    @Override
    public void parse(String value, Product product) {
        Type type = new Type(value);
        product.addType(type);
    }

    @Override
    public String getKey() {
        return "type";
    }
}
