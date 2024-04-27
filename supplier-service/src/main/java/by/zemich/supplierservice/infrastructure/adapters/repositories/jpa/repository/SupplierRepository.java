package by.zemich.supplierservice.infrastructure.adapters.repositories.jpa.repository;


import by.zemich.supplierservice.infrastructure.adapters.repositories.jpa.entity.SupplierEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity, UUID> {
    List<SupplierEntity> findAll();
    Optional<SupplierEntity> findByName(String name);

}
