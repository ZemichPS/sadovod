package by.zemich.uiservice.service;

import by.zemich.uiservice.dao.feignclient.ProductFeignClient;
import by.zemich.uiservice.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductFeignClient productFeignClient;

    public ProductService(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    public Page<ProductDto> getPage(int pageNo, int pageSize) {
        return productFeignClient.getPage(pageNo, pageSize);
    }
}
