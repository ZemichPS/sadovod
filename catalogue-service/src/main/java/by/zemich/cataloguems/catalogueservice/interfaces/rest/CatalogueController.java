package by.zemich.cataloguems.catalogueservice.interfaces.rest;

import by.zemich.cataloguems.catalogueservice.interfaces.rest.response.ProductCardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/catalogue")
public class CatalogueController {



    @GetMapping("/products")
    ResponseEntity<Page<ProductCardDto>> getAll(
            @RequestParam(name = "page_number", defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "page_size", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "asc_sort_field_name", defaultValue = "createdAt") String ascSortFieldName
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(Sort.Order.asc(ascSortFieldName))
        );


        return null;
    }
}
