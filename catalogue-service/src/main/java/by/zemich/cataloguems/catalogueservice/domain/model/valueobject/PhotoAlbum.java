package by.zemich.cataloguems.catalogueservice.domain.model.valueobject;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

public class PhotoAlbum {
    private final List<ProductImage> images;

    public PhotoAlbum() {
        this.images = new ArrayList<>();
    }



    public boolean addImage(ProductImage image) {
        return images.add(image);
    }

    public List<ProductImage> getAllProductImages() {
        return images.stream().map(ProductImage::clone).toList();
    }


}
