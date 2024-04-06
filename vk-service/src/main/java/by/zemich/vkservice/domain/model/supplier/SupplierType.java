package by.zemich.vkservice.domain.model.supplier;

public enum SupplierType {

    WOMEN_CLOTHES("женская одежда"),
    MAN_CLOTHES("мужская одежда"),
    OTHER("другое");

    SupplierType(String title) {
        this.title = title;
    }

    private final String title;

    public String getTitle() {
        return title;
    }
}
