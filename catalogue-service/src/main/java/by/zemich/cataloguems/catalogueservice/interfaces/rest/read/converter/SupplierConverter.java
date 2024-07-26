package by.zemich.cataloguems.catalogueservice.interfaces.rest.read.converter;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SupplierConverter implements Converter<Supplier, by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Supplier> {
    @Override
    public by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Supplier convert(Supplier source) {
        return new by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Supplier(source.getId().uuid(), source.getName());
    }
}
