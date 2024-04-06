package by.zemich.authorizationserver.core.model;

import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
}
