package by.zemich.cataloguems.catalogueservice.domain.model.entities;


import jakarta.persistence.*;

import java.util.List;

@Embeddable
public class ColorData {
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "product_card_colors", joinColumns = @JoinColumn(name = "product_card_uuid"))
    @Column(name = "color")
    private List<String> colorList;
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
