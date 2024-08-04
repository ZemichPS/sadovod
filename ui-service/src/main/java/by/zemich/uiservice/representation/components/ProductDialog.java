package by.zemich.uiservice.representation.components;


import by.zemich.uiservice.model.ProductDto;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Tag("details-dialog")
@CssImport("./styles/details-dialog.css")
@Getter()
public class ProductDialog extends Dialog {

    private final ProductDto product;

    public ProductDialog(ProductDto product) {
        this.product = product;

        String dialogTitle = product.getName();
        this.setHeaderTitle(dialogTitle);
        setWidth("800px");
        setHeight("1000px");

        add(createMainBlock(
                createMainImage(),
                createTextDetailsBlock()
        ));

        List<String> photoLinksList = product.getImageEntityList().stream().map(by.zemich.uiservice.model.Image::link).toList();
        add(new PhotoCarouselView(photoLinksList));

    }

    private HorizontalLayout createMainBlock(Image image, VerticalLayout blockTextLayout) {
        HorizontalLayout mainBlockContainer = new HorizontalLayout();
        mainBlockContainer.setSpacing(true);
        mainBlockContainer.setMargin(true);

        mainBlockContainer.add(image);
        mainBlockContainer.add(blockTextLayout);

        return mainBlockContainer;
    }

    private Image createMainImage() {

        String imageSrc = product.getImageEntityList()
                .stream()
                .map(by.zemich.uiservice.model.Image::link)
                .toList()
                .getFirst();

        Image mainImage = new Image();
        mainImage.setAlt("Product image");
        mainImage.setSrc(imageSrc);
        mainImage.addClassNames("main-dialog-image");

        return mainImage;
    }

    private VerticalLayout createTextDetailsBlock() {
        VerticalLayout textDetailsBlock = new VerticalLayout();
        textDetailsBlock.setSpacing(true);
        textDetailsBlock.setMargin(true);

        Span productName = new Span(product.getName());
        productName.addClassName("product-dialog-product-name");

        Span category = new Span(product.getCategory());
        Span conditionSelf;

        if (product.isAvailableInPieces()) {
            conditionSelf = new Span("продажа поштучно");
        } else {
            conditionSelf = new Span("продажа набором. Количество в наборе: " + product.getQuantityInPackage());
        }

        Paragraph paragraph = new Paragraph(
                product.getAttributeList()
                        .stream()
                        .map(attribute -> attribute.key() + ":" + attribute.value())
                        .collect(Collectors.joining("\n"))
        );

        paragraph.addClassName("product-dialog-paragraph");

        Span priceSpan = new Span(product.getOriginPrice().toString());
        priceSpan.addClassName("product-dialog-price");

        String color = product.isSale() ? "red" : "green";
        priceSpan.getElement().getStyle().set("color", color);

        textDetailsBlock.add(productName);
        textDetailsBlock.add(category);
        textDetailsBlock.add(conditionSelf);
        textDetailsBlock.add(paragraph);
        textDetailsBlock.add(priceSpan);

        return textDetailsBlock;
    }

}
