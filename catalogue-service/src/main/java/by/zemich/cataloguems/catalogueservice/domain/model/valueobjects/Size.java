package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Size {
    private String availableSizes;
    boolean inSize;

    public Size(String availableSizes, boolean inSize) {
        this.availableSizes = availableSizes;
        this.inSize = inSize;
    }

    public Size() {
    }
}
