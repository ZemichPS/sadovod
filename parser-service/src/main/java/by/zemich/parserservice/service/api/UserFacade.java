package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.ERole;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserFacade {
    UserDto create(UserDto newUser);
    UserDto getByTelegramId(Long telegramUserId);
    UserDto getByUsername(String username);
    List<UserDto> getByRole(ERole role);
    boolean existsByUsername(String username);
    boolean existsByUuid(UUID userUuid);
    boolean existsByTelegramUserId(Long userId);
    List<UserDto> getAll();
    UserDto getByUuid(UUID userUuid);
    UserDto update(UserDto updateForUpdate);
    UserDto delete(UserDto userDto);
    List<SupplierDto> getSupplierSetForUserByTelegramID(Long telegramUserId);



}
