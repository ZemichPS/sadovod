package by.zemich.userservice.service.impl;

import by.zemich.userservice.core.UserRole;
import by.zemich.userservice.core.UserStatus;
import by.zemich.userservice.core.dto.User;
import by.zemich.userservice.core.dto.UserCreateDto;
import by.zemich.userservice.dao.entity.UserEntity;
import by.zemich.userservice.dao.repository.UserRepository;
import by.zemich.userservice.service.api.UserService;
import com.fasterxml.uuid.Generators;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserEntity create(UserCreateDto userCreate) {
        UserEntity newUserEntity = mapper.map(userCreate, UserEntity.class);
        newUserEntity.setUuid(Generators.timeBasedGenerator().generate());
        newUserEntity.setStatus(UserStatus.WAITS_FOR_ACTIVATION);
        newUserEntity.setRole(UserRole.USER);

        return userRepository.save(newUserEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUuid(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity update(User user) {
        return null;
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public void deleteByUuid(User user) {
        UserEntity userEntityForDelete = mapper.map(user, UserEntity.class);
        userRepository.delete(userEntityForDelete);
    }


}
