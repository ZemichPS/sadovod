package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Attribute;
import by.zemich.cataloguems.catalogueservice.domain.service.parser.shared.ProductCharacteristicParser;

import java.util.HashMap;
import java.util.Map;

public class ParserHandler {
    private final Map<String, ProductCharacteristicParser> parserMap = new HashMap<>();

    void parse(Product product, Map<String, String> valuesMap){
        valuesMap.entrySet().stream().forEach(
                entry->{
                    String value = entry.getValue();
                    String key = entry.getKey();
                    ProductCharacteristicParser parser = parserMap.getOrDefault(key, new ProductCharacteristicParser() {
                        @Override
                        public void parse(String value, Product product) {
                            Attribute attribute = new Attribute(key, value);
                            product.addAttribute(attribute);
                        }
                        @Override
                        public String getKey() {
                            return "";
                        }
                    });
                    parser.parse(value, product);
                }
        );

    }

    void register(ProductCharacteristicParser characteristicParser){
        parserMap.put(characteristicParser.getKey(), characteristicParser);
    }
}
