package by.zemich.userservice.service.api;

import by.zemich.userservice.core.dto.User;
import by.zemich.userservice.core.dto.UserCreateDto;
import by.zemich.userservice.dao.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserEntity create(UserCreateDto userCreate);
    Optional<UserEntity> getByUuid(UUID uuid);
    Optional<UserEntity> getByEmail(String email);
    List<UserEntity> getAll();
    UserEntity update(User user);
    void deleteByUuid(UUID uuid);
    void deleteByUuid(User user);

}
