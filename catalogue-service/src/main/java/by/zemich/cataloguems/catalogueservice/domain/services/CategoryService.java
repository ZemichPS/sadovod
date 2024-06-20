package by.zemich.cataloguems.catalogueservice.domain.services;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Category;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CategoryService {
    public static List<Category> filterAndRetrieveCategories(List<Category> categories, Predicate<Category> categoryPredicate) {
        return categories
                .stream()
                .filter(categoryPredicate)
                .toList();
    }

    public static Optional<Category>  findCategory(List<Category> categories, Predicate<Category> categoryPredicate){
        return categories
                .stream()
                .filter(categoryPredicate)
                .findFirst();
    }

}
