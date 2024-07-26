package by.zemich.cataloguems.catalogueservice.interfaces.rest.read.converter;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter implements Converter<ProductImage, Image> {
    @Override
    public Image convert(ProductImage source) {
        return new Image(source.getUuid(), source.getImageLink().link());
    }
}
