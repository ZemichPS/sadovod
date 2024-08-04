package by.zemich.uiservice.representation.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

@CssImport("./styles/photo-carousel-view.css")
public class PhotoCarouselView extends VerticalLayout {

    private List<String> imageUrls; // URLs изображений
    private int currentIndex = 0;
    private Image imageDisplay;

    public PhotoCarouselView(List<String> imageUrlsList) {
        this.imageUrls = imageUrlsList;
        imageDisplay = new Image(imageUrls.get(currentIndex), "Фото товара");
        imageDisplay.setWidth("400px");
        imageDisplay.setHeight("300px");

        Button prevButton = new Button("Предыдущая", event -> showPreviousImage());
        Button nextButton = new Button("Следующая", event -> showNextImage());

        nextButton.addClassNames("v-carousel-button");
        prevButton.addClassNames("v-carousel-button");

        HorizontalLayout imageLayout = new HorizontalLayout(prevButton, imageDisplay, nextButton);
        imageLayout.setAlignItems(Alignment.CENTER);
        imageLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(imageLayout);
    }

    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            updateImage();
        }
    }

    private void showNextImage() {
        if (currentIndex < imageUrls.size() - 1) {
            currentIndex++;
            updateImage();
        }
    }

    private void updateImage() {
        imageDisplay.setSrc(imageUrls.get(currentIndex));
    }
}
