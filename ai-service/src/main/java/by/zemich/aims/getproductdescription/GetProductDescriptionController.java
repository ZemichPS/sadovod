package by.zemich.aims.getproductdescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/get_product_description")
@Slf4j
class GetProductDescriptionController {

    private final GetProductDescriptionServiceApi service;

    GetProductDescriptionController(GetProductDescriptionServiceApi service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> getProductDescriptionFromText(@RequestBody GetProductDescriptionRequest request) {

        GetProductDescriptionResponse jsonProductDescription = service.createJsonProductDescription(request);
        return ResponseEntity.ok(jsonProductDescription);
    }

}
