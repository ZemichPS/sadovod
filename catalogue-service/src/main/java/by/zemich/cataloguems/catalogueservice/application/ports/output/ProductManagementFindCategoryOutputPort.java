package by.zemich.cataloguems.catalogueservice.application.ports.output;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Category;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryType;

public interface ProductManagementFindCategoryOutputPort {
    Category findOrGetDefaultCategory(CategoryType categoryType);
}
