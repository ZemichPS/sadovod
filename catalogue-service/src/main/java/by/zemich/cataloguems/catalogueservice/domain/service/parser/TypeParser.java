package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Category;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.shared.ProductCharacteristicParser;

public class TypeParser implements ProductCharacteristicParser {
    @Override
    public void parse(String value, Product product) {
        Category category = new Category(value);
        product.addCategory(category);
    }

    @Override
    public String getKey() {
        return "type";
    }
}
