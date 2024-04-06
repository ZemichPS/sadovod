package by.zemich.parserservice.core.enums;

public enum CategoryClothing {
    TSHIRT("футболка"),
    DRESS("платье"),
    PANTS("брюки"),
    JEANS("джинсы");


    private String title;

    CategoryClothing(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
