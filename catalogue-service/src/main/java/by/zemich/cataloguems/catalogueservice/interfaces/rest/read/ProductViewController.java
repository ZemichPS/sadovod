package by.zemich.cataloguems.catalogueservice.interfaces.rest.read;

import by.zemich.cataloguems.catalogueservice.application.usecases.ProductViewUseCase;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.read.converter.ProductConverter;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/catalogue/products")
public class ProductViewController {
    private final ProductConverter productConverter;
    private final ProductViewUseCase productViewUseCase;

    public ProductViewController(ProductConverter productConverter,
                                 ProductViewUseCase productViewUseCase) {
        this.productConverter = productConverter;
        this.productViewUseCase = productViewUseCase;
    }

    @GetMapping("/all")
    public Mono<List<ProductDto>> getProducts() {
        final List<ProductDto> all = productViewUseCase.findAll().stream()
                .map(productConverter::convert)
                .toList();
        return Mono.fromCallable(() -> all);
    }
}
