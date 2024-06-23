package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa;

import by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {

}
