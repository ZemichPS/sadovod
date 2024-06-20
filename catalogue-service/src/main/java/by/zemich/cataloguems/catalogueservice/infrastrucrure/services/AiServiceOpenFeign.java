package by.zemich.cataloguems.catalogueservice.infrastrucrure.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("AI-SERVICE")
public interface AiServiceOpenFeign {

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/get_product_description")
    GetProductDescriptionResponse recognize(GetProductDescriptionRequest request);


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class GetProductDescriptionResponse {
        private String jsonDescription;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class GetProductDescriptionRequest {
        private String jsonDestination;
        private String source;
    }

}
