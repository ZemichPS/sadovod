package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa;

import by.zemich.cataloguems.catalogueservice.domain.model.aggregates.ProductCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProductCardRepository extends CrudRepository<ProductCard, UUID>,
        PagingAndSortingRepository<ProductCard, UUID> {
}
