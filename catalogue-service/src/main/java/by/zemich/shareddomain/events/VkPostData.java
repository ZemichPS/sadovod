package by.zemich.shareddomain.events;

import java.time.LocalDateTime;
import java.util.List;

public record VkPostData(List<String> imagesLinkList, LocalDateTime publishedAt, String text) {
}
