package by.zemich.gatewayservice.filters;

import by.zemich.gatewayservice.model.dto.ProductDto;
import by.zemich.gatewayservice.service.ProductService;
import by.zemich.gatewayservice.util.PageSerializationUtil;
import by.zemich.gatewayservice.util.PaginationListUtils;
import by.zemich.gatewayservice.util.ParamsUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
@Order(3)
public class ProductCacheFilter implements GatewayFilter {

    private final CacheManager cacheManager;
    private final ProductService productService;

    public ProductCacheFilter(CacheManager cacheManager,
                              ProductService productService) {
        this.cacheManager = cacheManager;
        this.productService = productService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final String path = exchange.getRequest().getURI().getPath();
        String end = path.split("/")[path.split("/").length - 1];

        String cacheKey = "key1";
        Cache cache = cacheManager.getCache("products");
        Cache.ValueWrapper cachedResponse = cache.get(cacheKey);

        int page = ParamsUtil.getIntParamByName(exchange, "page");
        int size = ParamsUtil.getIntParamByName(exchange, "size");

        if (end.startsWith("all")) {
            if (Objects.nonNull(cachedResponse)) {
                Page<ProductDto> requestedPage = getRequestedPageFromCache(page, size, cachedResponse);
                return writePageResponse(exchange, requestedPage);
            } else {
                return productService.getProducts()
                        .doOnNext(products -> cache.put(cacheKey, products))
                        .flatMap(products -> {
                            Page<ProductDto> requestedPage = getRequestedPageSourceList(page, size, products);
                            return writePageResponse(exchange, requestedPage);
                        });
            }
        }
        return chain.filter(exchange);
    }


    private Page<ProductDto> getRequestedPageFromCache(int pageNumber, int size, Cache.ValueWrapper cachedResponse) {
        if (Objects.isNull(cachedResponse)) return null;
        List<ProductDto> products = (List<ProductDto>) cachedResponse.get();
        return PaginationListUtils.returnPagedList(PageRequest.of(pageNumber, size), products);
    }

    private Page<ProductDto> getRequestedPageSourceList(int pageNumber, int size, List<ProductDto> products) {
        return PaginationListUtils.returnPagedList(PageRequest.of(pageNumber, size), products);
    }

    private Mono<Void> writePageResponse(ServerWebExchange exchange, Page<?> page) {
        try {
            byte[] responseBody = PageSerializationUtil.serialize(page).getBytes();
            exchange.getResponse().getHeaders().setContentLength(responseBody.length);
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(responseBody)));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
