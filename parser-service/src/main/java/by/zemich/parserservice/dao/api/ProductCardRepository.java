package by.zemich.parserservice.dao.api;

import by.zemich.parserservice.dao.entity.ProductCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCardRepository extends JpaRepository<ProductCardEntity, UUID> {


}
