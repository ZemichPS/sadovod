package by.zemich.gatewayservice.service;

import by.zemich.gatewayservice.model.dto.ProductDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<List<ProductDto>> getProducts() {
        final Mono<List<ProductDto>> listMono = webClient.get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductDto>>() {
                });

        return listMono;
    }
}
