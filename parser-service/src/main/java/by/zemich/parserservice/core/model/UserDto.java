package by.zemich.parserservice.core.model;

import by.zemich.parserservice.core.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID uuid;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
    private Long telegramId;
    private Long chatID;
    private String username;
    private String firstname;
    private String lastname;
    private ERole role;
    private EUserStatus status;
    private List<SupplierDto> suppliers;

}
