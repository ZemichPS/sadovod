package by.zemich.cataloguems.catalogueservice.interfaces.rest.read.converter;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Attribute;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeConverter implements Converter<Attribute, by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Attribute> {
    @Override
    public by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Attribute convert(Attribute source) {
        return new by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Attribute(source.key(), source.value());
    }
}
