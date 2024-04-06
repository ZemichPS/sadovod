package by.zemich.parserservice.dao.api;


import by.zemich.parserservice.dao.entity.SupplierEntity;
import by.zemich.parserservice.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity, UUID> {
    List<SupplierEntity> findAll();
    List<SupplierEntity> findAllByUser(UserEntity userEntity);

}
