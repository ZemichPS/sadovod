package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("gateway")
public interface AiServiceOpenFeign {

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/get_product_description")
    String proceed(String sourceText);
}
