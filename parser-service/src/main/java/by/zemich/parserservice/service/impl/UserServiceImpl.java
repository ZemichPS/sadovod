package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.model.ERole;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.api.UserRepository;
import by.zemich.parserservice.dao.entity.UserEntity;
import by.zemich.parserservice.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userDao;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public UserEntity create(UserDto newUser) {
        UserEntity newUserEntity = modelMapper.map(newUser, UserEntity.class);
        return userDao.save(newUserEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByTelegramId(Long telegramUserId) {
        return userDao.findByTelegramId(telegramUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getByRole(ERole role) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public boolean existsByUserUuid(UUID userUuid) {
        return userDao.existsById(userUuid);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public boolean existsByTelegramUserId(Long userId) {
        return userDao.existsByTelegramId(userId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<UserEntity> getAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUuid(UUID userUuid) {
        return userDao.findById(userUuid);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserEntity update(UserDto updateUser) {
        UserEntity userEntity = modelMapper.map(updateUser, UserEntity.class);
        return userDao.save(userEntity);
    }

    @Override
    public UserEntity delete(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userDao.delete(userEntity);
        return userEntity;
    }
}
