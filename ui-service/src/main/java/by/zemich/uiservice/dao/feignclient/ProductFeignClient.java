package by.zemich.uiservice.dao.feignclient;

import by.zemich.uiservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "gateway")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/catalogue/get_page")
    Page<Product> getPage(@RequestParam int pageNo, @RequestParam int pageSize);
}
