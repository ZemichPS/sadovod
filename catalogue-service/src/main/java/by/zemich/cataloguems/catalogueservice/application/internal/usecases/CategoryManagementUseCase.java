package by.zemich.cataloguems.catalogueservice.application.internal.usecases;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Category;
import by.zemich.cataloguems.catalogueservice.domain.model.entities.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryID;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryType;

import java.util.List;

public interface CategoryManagementUseCase {

    Category createCategory(CategoryID id,
                            CategoryType type,
                            List<Product> products,
                            Category parrentCategory,
                            List<Category> subcategories);
}
