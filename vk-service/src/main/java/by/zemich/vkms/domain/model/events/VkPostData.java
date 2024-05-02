package by.zemich.vkms.domain.model.events;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public record VkPostData(List<URI> imagesLinkList, LocalDateTime publishedAt, String text) {
}
