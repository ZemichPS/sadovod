package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository;

import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ImageEntity;
import org.apache.kafka.common.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Uuid> {
}
