package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Description {
    private Color color;
    private Size size;
    private String detailed;

    public Description() {
    }

    public Description(Color color, Size size, String detailed) {
        this.color = color;
        this.size = size;
        this.detailed = detailed;
    }
}
