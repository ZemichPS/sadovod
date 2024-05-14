package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Description {
    @ManyToOne
    private Material material;
    @Embedded
    private ColorData colorData;
    @Embedded
    private SizeData sizeData;
    private String quality;

    public Description(Material material, ColorData colorData, SizeData sizeData, String quality) {
        this.material = material;
        this.colorData = colorData;
        this.sizeData = sizeData;
        this.quality = quality;
    }

    public Description() {
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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
}
