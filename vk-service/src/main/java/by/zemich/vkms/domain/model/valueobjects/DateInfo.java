package by.zemich.vkms.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class DateInfo {
    private LocalDateTime publishedAt;
    private LocalDateTime savedAt;

    public DateInfo(LocalDateTime publishedAt, LocalDateTime savedAt) {
        this.publishedAt = publishedAt;
        this.savedAt = savedAt;
    }

    public DateInfo(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
