package by.zemich.vkms.infrastructure.output.repositories.jpa;

import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.SupplierEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SupplierRepository extends CrudRepository<SupplierEntity, UUID> {
}
