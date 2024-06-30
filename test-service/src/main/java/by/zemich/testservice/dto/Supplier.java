package by.zemich.testservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Supplier {
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String vkId;
    private String vkLink;
    private String address;
    private String phone;
    private Specialization specialization;

    public enum Specialization{
        DRESS, OTHER, WOMEN_CLOTHES
    }
}
