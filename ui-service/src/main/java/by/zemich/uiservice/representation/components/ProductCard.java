package by.zemich.uiservice.representation.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ProductCard extends VerticalLayout {
    private Image image;
    private UUID uuid;
    private Span name;
    private Span supplierName;
    private Paragraph description;
    private Span price;

    public ProductCard(List<String> images,
                       UUID uuid,
                       String name,
                       String supplierName,
                       String price,
                       String description
    ) {
        String url = images.getFirst();

        this.image = new Image(url, "Product name");
        this.description = new Paragraph(description);
        this.price = new Span(price);
        this.name = new Span(name);
        this.uuid = uuid;
        this.supplierName = new Span(supplierName);

        image.setWidth("100%");
        this.name.getElement().getStyle().set("font-weight", "bold");
        this.price.getElement().getStyle().set("color", "green");

        add(this.image,
                this.name,
                this.supplierName,
                this.description,
                this.price
        );

        setSpacing(false);
        setPadding(false);
        setWidth("500px");
        getStyle().set("border", "1px solid #e0e0e0")
                .set("border-radius", "8px")
                .set("padding", "16px")
                .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");
    }


}
