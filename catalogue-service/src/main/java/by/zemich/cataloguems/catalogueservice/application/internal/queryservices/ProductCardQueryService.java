package by.zemich.cataloguems.catalogueservice.application.internal.queryservices;

import by.zemich.cataloguems.catalogueservice.domain.commands.GetAllProductCardsQuery;
import by.zemich.cataloguems.catalogueservice.domain.model.aggregates.ProductCard;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.ProductCardRepository;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.response.ProductCardDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCardQueryService {
    private final ProductCardRepository repository;
    private final ModelMapper modelMapper;

    public ProductCardQueryService(ProductCardRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Page<ProductCardDto> getAll(GetAllProductCardsQuery query) {

        Page<ProductCard> productCardsPage = repository.findAll(query.getPageable());
        List<ProductCardDto> productCardDtos = productCardsPage.getContent().stream()
                .map(productCard -> modelMapper.map(productCard, ProductCardDto.class))
                .toList();

        return new PageImpl<ProductCardDto>(
                productCardDtos,
                productCardsPage.getPageable(),
                productCardsPage.getTotalElements()
        );
    }

}
