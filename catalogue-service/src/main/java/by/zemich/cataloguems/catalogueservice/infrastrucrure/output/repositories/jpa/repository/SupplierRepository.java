package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.repository;

import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.SupplierEntity;
import org.apache.kafka.common.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
}
