package by.zemich.aims.getproductdescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/ai")
@Slf4j
class GetProductDescriptionController {

  private final GetProductDescriptionService aiService;

    GetProductDescriptionController(GetProductDescriptionService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/get_product_description")
    public ResponseEntity<String> getProductDescriptionFromText(@RequestBody String requestBody) throws IOException {
        String response = aiService.getProductDescription(requestBody);
        return ResponseEntity.ok(response);
    }

}
