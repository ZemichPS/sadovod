package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository;

import by.zemich.cataloguems.catalogueservice.domain.model.projections.ProductDto;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Override
    List<ProductEntity> findAll();
}
