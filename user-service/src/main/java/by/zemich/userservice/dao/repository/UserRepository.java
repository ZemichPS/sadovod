package by.zemich.userservice.dao.repository;

import by.zemich.userservice.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByEmail(String email);

}
