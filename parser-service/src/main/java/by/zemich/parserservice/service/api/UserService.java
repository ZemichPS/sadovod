package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.ERole;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserEntity create(UserDto newUser);
    Optional<UserEntity> getByTelegramId(Long telegramUserId);
    Optional<UserEntity> getByUsername(String username);
    List<UserEntity> getByRole(ERole role);
    boolean existsByUsername(String username);
    boolean existsByUserUuid(UUID uuid);
    boolean existsByTelegramUserId(Long userId);
    List<UserEntity> getAll();
    Optional<UserEntity> getByUuid(UUID userUuid);
    UserEntity update(UserDto newUser);
    UserEntity delete(UserDto userDto);


}
