package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories;

import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    
}
