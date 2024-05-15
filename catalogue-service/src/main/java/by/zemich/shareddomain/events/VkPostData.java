package by.zemich.shareddomain.events;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record VkPostData(
        @NotEmpty(message = "Image list link must not be empty")
        List<String> imagesLinkList,
        @NotNull(message = "Published time must not be null.")
        LocalDateTime publishedAt,
        @NotBlank(message = "Post text must not be empty.")
        String text) {
}
