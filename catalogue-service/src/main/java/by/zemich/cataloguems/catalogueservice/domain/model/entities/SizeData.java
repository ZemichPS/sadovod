package by.zemich.cataloguems.catalogueservice.domain.model.entities;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.List;

@Embeddable
public class SizeData {
    private String sizeRange;
    @ElementCollection
    private List<Integer> sizeList;
    boolean inSize;

    public SizeData(String sizeRange, List<Integer> sizeList, boolean inSize) {
        this.sizeRange = sizeRange;
        this.sizeList = sizeList;
        this.inSize = inSize;
    }

    public SizeData() {
    }

    public SizeData(List<Integer> sizeList) {
        this.sizeList = sizeList;
    }

    public String getSizeRange() {
        return sizeRange;
    }

    public void setSizeRange(String sizeRange) {
        this.sizeRange = sizeRange;
    }

    public List<Integer> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Integer> sizeList) {
        this.sizeList = sizeList;
    }

    public boolean isInSize() {
        return inSize;
    }

    public void setInSize(boolean inSize) {
        this.inSize = inSize;
    }
}
