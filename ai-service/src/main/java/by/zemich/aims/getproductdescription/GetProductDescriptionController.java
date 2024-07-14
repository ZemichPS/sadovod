package by.zemich.aims.getproductdescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/get_product_description")
@Slf4j
class GetProductDescriptionController {

    @Autowired
    private List<GetProductDescriptionServiceApi> aiServices;


    @GetMapping
    public ResponseEntity<String> getProductDescriptionFromText(@RequestParam String request) {
        String response = aiServices.getFirst().getProductDescription(request);
        return ResponseEntity.ok(response);
    }

}
