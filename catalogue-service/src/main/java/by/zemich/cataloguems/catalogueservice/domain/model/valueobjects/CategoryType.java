package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

public enum CategoryType {


    ELECTRONICS(false, "Электроника"),
    LAPTOPS(true, "Ноутбуки"),
    PHONES(true, "Смартфоны"),
    CLOTHING(false, "Одежда"),
    SHIRTS(true, "Майки"),
    PANTS(true, "Штаны");

    private boolean isSubCategory;
    private String ruTitle;

    CategoryType(boolean isSubCategory, String ruTitle){
        this.isSubCategory = isSubCategory;
        this.ruTitle = ruTitle;
    }

    public boolean isSubCategory(){
        return isSubCategory;
    }

    public String getRuTitle() {
        return ruTitle;
    }
}
