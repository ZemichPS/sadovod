package by.zemich.cataloguems.catalogueservice.domain.model.entities;


import jakarta.persistence.*;

import java.util.List;

@Embeddable
public class SizeData {
    private String sizeRange;
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_uuid"))
    @Column(name = "size")
    private List<String> sizeList;
    boolean inSize;

    public SizeData(String sizeRange, List<String> sizeList, boolean inSize) {
        this.sizeRange = sizeRange;
        this.sizeList = sizeList;
        this.inSize = inSize;
    }

    public SizeData() {
    }

    public SizeData(List<String> sizeList) {
        this.sizeList = sizeList;
    }

    public String getSizeRange() {
        return sizeRange;
    }

    public void setSizeRange(String sizeRange) {
        this.sizeRange = sizeRange;
    }

    public List<String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<String> sizeList) {
        this.sizeList = sizeList;
    }

    public boolean isInSize() {
        return inSize;
    }

    public void setInSize(boolean inSize) {
        this.inSize = inSize;
    }
}
