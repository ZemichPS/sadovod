package by.zemich.textprocessormicroservice.interfaces.rest.request;

import java.util.ArrayList;
import java.util.List;

public class ItemTemplate {
    private String productType = "";
    private List<String> colors = new ArrayList<>();
    private Material material = new Material("", "");
    private List<Integer> sizes = new ArrayList<>();
    private Prices prices = new Prices(Double.valueOf(0), Double.valueOf(0));

    public ItemTemplate(String productType, List<String> colors, Material material, List<Integer> sizes, Prices prices) {
        this.productType = productType;
        this.colors = colors;
        this.material = material;
        this.sizes = sizes;
        this.prices = prices;
    }

    public ItemTemplate() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(List<Integer> sizes) {
        this.sizes = sizes;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "ItemTemplate{" +
                "productType='" + productType + '\'' +
                ", colors=" + colors +
                ", material=" + material +
                ", sizes=" + sizes +
                ", prices=" + prices +
                '}';
    }
}
