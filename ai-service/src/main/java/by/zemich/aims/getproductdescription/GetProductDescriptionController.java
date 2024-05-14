package by.zemich.aims.getproductdescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ai-service/product_description")
@Slf4j
class GetProductDescriptionController {

    private final GetProductDescriptionService service;

    public GetProductDescriptionController(GetProductDescriptionService service) {
        this.service = service;
    }

    @PostMapping("/v1/api/")
    public ResponseEntity<?> getProductDescriptionFromText(@RequestBody GetProductDescriptionRequest request) {

        log.info(request.getSource());
        log.info(request.getJsonDestination());

        GetProductDescriptionResponse jsonProductDescription = service.createJsonProductDescription(request);

        log.info(jsonProductDescription.getJsonDescription());

        return ResponseEntity.ok(jsonProductDescription);
    }

}
