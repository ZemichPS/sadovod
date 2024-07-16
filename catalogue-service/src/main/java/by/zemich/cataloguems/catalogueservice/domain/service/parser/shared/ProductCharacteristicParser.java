package by.zemich.cataloguems.catalogueservice.domain.service.parser.shared;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;

public interface ProductCharacteristicParser {
     void parse(String value, Product product);
     String getKey();
}
