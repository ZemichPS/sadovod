package by.zemich.uiservice.representation;

import by.zemich.uiservice.model.ProductDto;
import by.zemich.uiservice.representation.components.ProductCard;
import by.zemich.uiservice.representation.components.ProductDialog;
import by.zemich.uiservice.service.ProductService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Route("sadovod/")
public class Application extends AppLayout {
    private final ProductService productService;

    public Application(ProductService productService, ProductService productService1) {
        this.productService = productService1;
        createUI();
    }

    private void createUI() {
        createTitle("Sadovod");

        Div avatarDiv = new Div(getAvatar());

        this.addToNavbar(true,
                createDrawerToggle(),
                createTitle("Sadovod"),
                avatarDiv
        );
        this.addToDrawer(createHorizontalMenu());
        this.setContent(createProductCardLayout(1, 20));

    }

    private H1 createTitle(String applicationName) {
        H1 appTitle = new H1(applicationName);
        appTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");

        return appTitle;
    }

    private DrawerToggle createDrawerToggle() {
        DrawerToggle toggle = new DrawerToggle();

        return toggle;
    }

    private Tabs createHorizontalMenu() {
        Tab viewTab = new Tab(VaadinIcon.CARET_DOWN.create(), new Span("Products"));
        Tab modifyTab = new Tab(VaadinIcon.USER.create(), new Span("Suppliers"));
        Tab orderTab = new Tab(VaadinIcon.BOOK.create(), new Span("Orders"));

        Tabs tabs = new Tabs();
        tabs.add(viewTab, modifyTab, orderTab);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        return tabs;
    }

    private Avatar getAvatar() {
        String username = "User";
        String avatarUri = "https://yandex-images.clstorage.net/9Ztsw5400/ac9fb1ze2dw/0m3iz8OSyHzNnMA1ocuRmrRQgRl3TaCFxnyvucXJUJpIdmchetIY7B3FxQxnSLSr8K19GUr_SRgsIQD2M_zzIId8N5_Skgl-iQ6HMTEPktnkk4SN5MyzUUT8rbhK0w6HVew4LWaaU7POkdONJ1uzvuLi8khbZZgJsL0E9yms5jDQIGaKi8cFPofJC_Hgjt5eK18N16TEPw9Mbjfpz1cFBVXo-yJl35UxwpgRjy6e7LbpJjcdl2UUD_F3DnAi5Cd1HiwhgYZMy37NxoW1oE_SUG5YwlLtAjQGA-r9J4bfww3Uq7YoaRUbPItdEwqlFSw64OZ2mNRiGUBwOwf8q-qhttOta0ZCC0Z4ysuDOSbAEFTkEUIV5sWzystpfW-JX4fC0ae_Z6CSELiL3J1NbN3tuO2jdx2SqFDEM_-LeyqjorbXIyBCg4SKfEuKBLTkCtuYK9UBUG5DNcbMbnuqgZYKDhxn8GSnURzxxFPWBqqfKnxn4T9UX2PXx3D0xDkhqaxxHKivg82JBviAz8J7a0zRlyzagtAoRrzJy-g7L4xeg42ZJXVv4dvRucQflkbiHGq9YqZw0dLtnA97NwS45OrivhZlZ8_FgoN9AwPD9-HA0RIhWYlVoMM0B8IiPGWGEcNKnKZ_JGFQUXSEmFWBp5sldOfmcNQXJ52HtXcPfKbhZLnS5urOyUJK8USOzzwuyJBRZRtJleLCtMYPqvDpQ1bFyx7vPy9mmp9wCBVeQO_e7r3tKXldFuoWDrK0BLlkLubzGCVjiURCA3NFwky-a0gYGi-RDJbuhvkOhq6wpcHejwmQrzqr4NBYeQMY3QhuWSm8oOC5FFNqlYA188O0LGEicNJkYc6EgACwgQ4PeKEBmJVjmYNWpon1B0opMijG3IxD3-NwZOkQ0vmMXRWGJpXrMqXm9V2QItEOufuMsG3jajEYKGYFQ0nK_MVJT_SjCtCUIdeEFCcDd8ZBbzrmyA";

        return new Avatar(username, avatarUri);
    }

    private FlexLayout createProductCardLayout(int pageNumber, int pageSize) {
        FlexLayout layout = new FlexLayout();
        layout.setJustifyContentMode(FlexLayout.JustifyContentMode.CENTER);
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        final Page<ProductDto> page = productService.getPage(pageNumber, pageSize);
        page.getContent()
                .stream()
                .map(product -> {
                    ProductCard productCard = new ProductCard(
                            product.getImageEntityList().stream().map(image -> image.link()).toList(),
                            product.getUuid(),
                            product.getName(),
                            product.getSupplier().name(),
                            product.getOriginPrice().toString(),
                            product.getAttributeList().stream().map(category -> category.key() + " : " + category.value()).collect(Collectors.joining("\n"))
                    );
                    productCard.addClickListenerToDetailsButton(buttonClickEvent -> new ProductDialog(product).open());
                    return productCard;
                })
                .forEach(layout::add);

        return layout;
    }

    private FlexLayout createFakeProductCardLayout() {
        FlexLayout layout = new FlexLayout();
        layout.setJustifyContentMode(FlexLayout.JustifyContentMode.CENTER);
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        for (int i = 0; i < 25; i++) {
            ProductCard productCard = new ProductCard(
                    List.of("https://imgcdn.loverepublic.ru/upload/images/62542/625428704_70.jpg"),
                    UUID.randomUUID(),
                    "Костюмчик",
                    "Ахмат чай",
                    "15.88",
                    """
                            цвета: зелёный, крассный,белый
                            размеры: XL, ML, L
                            """
            );
            layout.add(productCard);
        }
        return layout;
    }

}
