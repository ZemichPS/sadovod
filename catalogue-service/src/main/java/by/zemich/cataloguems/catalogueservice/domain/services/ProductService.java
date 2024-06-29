package by.zemich.cataloguems.catalogueservice.domain.services;

import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDto;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ProductService {
    public static List<Product> filterAndRetrieveCategories(List<Product> categories, Predicate<Product> categoryPredicate) {
        return categories
                .stream()
                .filter(categoryPredicate)
                .toList();
    }

    public static Optional<Product> findCategory(List<Product> categories, Predicate<Product> categoryPredicate) {
        return categories
                .stream()
                .filter(categoryPredicate)
                .findFirst();
    }

    public static Description getDescriptionOf(ProductDto productDto) {
        Color color = new Color();
        List<String> availableColors = new ArrayList<>();
        if (Objects.nonNull(productDto.getAvailableColors())) availableColors = productDto.getAvailableColors();
        color.setAvailableColors(availableColors);
        color.setColorChoice(productDto.isAbilityToSelectColor());
        color.setPictureChoice(productDto.isAbilityToSelectPicture());

        Size size = new Size();
        size.setInSize(productDto.isInSize());
        size.setAvailableSizes(size.getAvailableSizes());

        String detailed = productDto.getDetailedDescription();

        return new Description(color, size, detailed);
    }

    public static Price getPriceOf(ProductDto productDto) {
        return new Price(
                643,
                productDto.getPriceForPiece(),
                productDto.getPriceForSet()
        );
    }

    public static ProductName getProductNameOf(ProductDto productDto) {
        return new ProductName(productDto.getProductName());
    }


}
