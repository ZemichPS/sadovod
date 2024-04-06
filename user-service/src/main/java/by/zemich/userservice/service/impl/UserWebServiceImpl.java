package by.zemich.userservice.service.impl;

import by.zemich.userservice.core.dto.User;
import by.zemich.userservice.core.dto.UserCreateDto;
import by.zemich.userservice.core.exception.UserNotFoundException;
import by.zemich.userservice.dao.entity.UserEntity;
import by.zemich.userservice.service.api.UserService;
import by.zemich.userservice.service.api.UserWebService;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class UserWebServiceImpl implements UserWebService {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserWebServiceImpl(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public ServerResponse save(ServerRequest request) {
        UserCreateDto userCreate = null;
        try {
            userCreate = request.body(UserCreateDto.class);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }


        UserEntity savedUserEntity = userService.create(userCreate);
        User savedUser = modelMapper.map(savedUserEntity, User.class);
        return ServerResponse.ok().body(savedUser);
    }

    @Override
    public ServerResponse update(ServerRequest request) {
        return null;
    }

    @Override
    public ServerResponse getByUuid(ServerRequest request) {
        return null;
    }

    @Override
    public ServerResponse getByEmailAddress(ServerRequest request) {
        String email = request.param ("email")
                .orElseThrow(BadRequestException::new);
        User foundUser = userService.getByEmail(email)
                .map(entity -> modelMapper.map(entity, User.class))
                .orElseThrow(() -> new UserNotFoundException("User with email %s doesn't exists".formatted(email)));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(foundUser);
    }

    @Override
    public ServerResponse getAll(ServerRequest request) {
        List<User> userList = userService.getAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .toList();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userList);
    }

    @Override
    public ServerResponse delete(ServerRequest request) {
        return null;
    }
}
