package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Description {
    private String materialName;
    @Embedded
    private ColorData colorData;
    @Embedded
    private SizeData sizeData;
    private String quality;

    public Description(String materialName,
                       ColorData colorData,
                       SizeData sizeData,
                       String quality) {
        this.materialName = materialName;
        this.colorData = colorData;
        this.sizeData = sizeData;
        this.quality = quality;
    }

    public Description() {
    }

    public ColorData getColorData() {
        return colorData;
    }

    public void setColorData(ColorData colorData) {
        this.colorData = colorData;
    }

    public SizeData getSizeData() {
        return sizeData;
    }

    public void setSizeData(SizeData sizeData) {
        this.sizeData = sizeData;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
