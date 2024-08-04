package by.zemich.uiservice.dao.feignclient;

import by.zemich.uiservice.model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "gateway")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/catalogue/products/all")
    Page<ProductDto> getPage(@RequestParam int page, @RequestParam int size);
}
