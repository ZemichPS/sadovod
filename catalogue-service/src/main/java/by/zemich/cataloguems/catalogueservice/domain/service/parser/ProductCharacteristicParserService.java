package by.zemich.cataloguems.catalogueservice.domain.service.parser;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;

import java.util.Map;
import java.util.stream.Collectors;

public class ProductCharacteristicParserService {
    ParserHandler parserHandler = new ParserHandler();

    {
        parserHandler.register(new PriceParser());
        parserHandler.register(new QuantityInSetParser());
        parserHandler.register(new TypeParser());
        parserHandler.register(new NameParser());
    }

    public void parse(Product product, Map<String, Object> sourseMap) {
        Map<String, String> mapped = sourseMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (String) entry.getValue()
                ));
        parserHandler.parse(product,mapped);
    }

}
