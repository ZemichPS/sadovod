package by.zemich.vkms.infrastructure.output.repositories.jpa;

import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {

}
