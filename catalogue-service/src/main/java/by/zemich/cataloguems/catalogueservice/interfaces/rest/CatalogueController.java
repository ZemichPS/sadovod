package by.zemich.cataloguems.catalogueservice.interfaces.rest;

import by.zemich.cataloguems.catalogueservice.application.internal.queryservices.ProductCardQueryService;
import by.zemich.cataloguems.catalogueservice.domain.commands.GetAllProductCardsQuery;
import by.zemich.cataloguems.catalogueservice.domain.model.aggregates.ProductCard;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.response.ProductCardDto;
import jakarta.ws.rs.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogue")
public class CatalogueController {
    private final ModelMapper modelMapper;

    private final ProductCardQueryService queryService;

    public CatalogueController(ModelMapper modelMapper, ProductCardQueryService queryService) {
        this.modelMapper = modelMapper;
        this.queryService = queryService;
    }

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

        GetAllProductCardsQuery query = new GetAllProductCardsQuery(pageable);
        Page<ProductCardDto> productCardDtoPage = queryService.getAll(query);
        return ResponseEntity.ok(productCardDtoPage);
    }
}
