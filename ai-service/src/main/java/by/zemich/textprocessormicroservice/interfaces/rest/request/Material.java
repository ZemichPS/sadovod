package by.zemich.textprocessormicroservice.interfaces.rest.request;

public class Material {
    private String type;
    private String quality;

    public Material(String type, String quality) {
        this.type = type;
        this.quality = quality;
    }

    public Material() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Material{" +
                "type='" + type + '\'' +
                ", quality='" + quality + '\'' +
                '}';
    }
}
