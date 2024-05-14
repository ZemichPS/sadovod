package by.zemich.cataloguems.catalogueservice.domain.model.entities;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.List;

@Embeddable
public class ColorData {
    @ElementCollection
    List<String> colorList;
    boolean colorChoice;
    boolean pictureChoice;

    public ColorData(List<String> colorList, boolean colorChoice, boolean pictureChoice) {
        this.colorList = colorList;
        this.colorChoice = colorChoice;
        this.pictureChoice = pictureChoice;
    }

    public ColorData(List<String> colorList) {
        this.colorList = colorList;
    }

    public ColorData() {
    }



    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public boolean isColorChoice() {
        return colorChoice;
    }

    public void setColorChoice(boolean colorChoice) {
        this.colorChoice = colorChoice;
    }

    public boolean isPictureChoice() {
        return pictureChoice;
    }

    public void setPictureChoice(boolean pictureChoice) {
        this.pictureChoice = pictureChoice;
    }
}
