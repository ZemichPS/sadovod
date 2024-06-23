package by.zemich.cataloguems.catalogueservice.application.usecases;

import by.zemich.cataloguems.catalogueservice.domain.model.entities.Category;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryID;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryType;

import java.util.List;

public interface CategoryManagementUseCase {

    Category createCategory(CategoryID id,
                            CategoryType type,
                            CategoryID parrentCategoryId,
                            List<CategoryID> subcategoryIds);
}
