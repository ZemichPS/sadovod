package by.zemich.cataloguems.catalogueservice.domain.model.entities;


import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryID;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public class Category {

    @Getter
    @Setter
    private CategoryID id;

    @Getter
    @Setter
    private CategoryType type;

    @Getter
    @Setter
    private List<Product> products;

    @Setter
    @Getter
    private Category parrentCategory;

    @Getter
    private List<Category> subcategories;

    public static Predicate<Category> getCategoryByType(CategoryType categoryType){
        return category -> category.getType().equals(categoryType);
    }

    public void addProduct(Product newProduct){
        //TODO добавить проверку через спецификацию
        this.products.add(newProduct);
    }

    public void addSubCategory(Category newCategory){
        //TODO добавить проверку через спецификацию
        this.subcategories.add(newCategory);
    }

    public boolean isSubCategory(){
        return Objects.nonNull(parrentCategory);
    }

}
