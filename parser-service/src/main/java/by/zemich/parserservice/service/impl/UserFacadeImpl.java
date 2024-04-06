package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.exeption.NotFoundUserException;
import by.zemich.parserservice.core.model.ERole;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.entity.UserEntity;
import by.zemich.parserservice.service.api.UserFacade;
import by.zemich.parserservice.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserFacadeImpl implements UserFacade {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserFacadeImpl(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public UserDto create(UserDto newUser) {
        UserEntity userEntity = userService.create(newUser);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto getByTelegramId(Long telegramUserId) {
        return userService.getByTelegramId(telegramUserId)
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .orElseThrow(() -> new NotFoundUserException(telegramUserId.toString()));
    }

    @Transactional
    @Override
    public UserDto getByUsername(String username) {
        return userService.getByUsername(username)
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .orElseThrow(() -> new NotFoundUserException(username));
    }

    @Override
    public List<UserDto> getByRole(ERole role) {
        return userService.getByRole(role).stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .toList();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userService.existsByUsername(username);
    }

    @Override
    public boolean existsByUuid(UUID userUuid) {
        return userService.existsByUserUuid(userUuid);
    }

    @Override
    public boolean existsByTelegramUserId(Long userId) {
        return userService.existsByTelegramUserId(userId);
    }

    @Override
    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .toList();
    }

    @Override
    public UserDto getByUuid(UUID userUuid) {
        UserEntity userEntity = userService.getByUuid(userUuid).orElseThrow(() -> new NotFoundUserException(userUuid.toString()));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto update(UserDto updateForUpdate) {
        return modelMapper.map(userService.update(updateForUpdate), UserDto.class);
    }

    @Override
    public UserDto delete(UserDto userDto) {
        userService.delete(userDto);
        return userDto;
    }

    @Override
    public List<SupplierDto> getSupplierSetForUserByTelegramID(Long telegramUserId) {
        UserDto concreteUser = modelMapper.map(userService.getByTelegramId(telegramUserId), UserDto.class);
        return concreteUser.getSuppliers();
    }
}
