package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "gateway")
public interface AiServiceOpenFeign {
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/ai/get_product_description")
    String proceed(@RequestBody String requestBody);
}
