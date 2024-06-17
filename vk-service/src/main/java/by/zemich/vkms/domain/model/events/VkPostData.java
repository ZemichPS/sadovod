package by.zemich.vkms.domain.model.events;

import java.time.LocalDateTime;
import java.util.List;

public record VkPostData(List<String> imagesLinkList, LocalDateTime publishedAt, String text) {
}
