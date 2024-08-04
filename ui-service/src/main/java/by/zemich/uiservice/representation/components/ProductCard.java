package by.zemich.uiservice.representation.components;

import com.vaadin.flow.component.*;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import lombok.Getter;

import java.util.List;
import java.util.UUID;


@Tag("product-card")
@CssImport("./styles/product-card.css")
@Getter()
public class ProductCard extends Component implements HasComponents {
    private Image image;
    private UUID uuid;
    private Span nameSpan;
    private Span supplierNameSpan;
    private Paragraph descriptionParagraph;
    private Span priceSpan;
    private Button detailsButton;

    public ProductCard(List<String> images,
                       UUID uuid,
                       String name,
                       String supplierName,
                       String price,
                       String description
    ) {
        String url = images.getFirst();

        image = new Image(url, "Product name");
        descriptionParagraph = new Paragraph(description);

        priceSpan = new Span(price);
        priceSpan.addClassName("price");

        nameSpan = new Span(name);
        this.uuid = uuid;
        supplierNameSpan = new Span(supplierName);
        detailsButton = new Button("Detail...");
        detailsButton.setClassName("details-button");


        //this.price.getElement().getStyle().set("color", "red");

        add(this.image,
                nameSpan,
                this.supplierNameSpan,
                descriptionParagraph,
                priceSpan,
                detailsButton
        );

        addClassName("product-card");
    }

    public void addClickListenerToDetailsButton(ComponentEventListener<ClickEvent<Button>> listener){
        detailsButton.addClickListener(listener);
    }


}
