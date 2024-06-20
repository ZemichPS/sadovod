package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Color {
    private List<String> availableColors;
    boolean colorChoice;
    boolean pictureChoice;

    public Color(List<String> availableColors, boolean colorChoice, boolean pictureChoice) {
        this.availableColors = availableColors;
        this.colorChoice = colorChoice;
        this.pictureChoice = pictureChoice;
    }


    public Color() {
    }
}
