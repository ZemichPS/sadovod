package by.zemich.userservice.core.dto;

import by.zemich.userservice.core.UserRole;
import by.zemich.userservice.core.UserStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class User {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private UserStatus status;
    private String viberChannelApiKey;
    private String telegramUsername;
}
