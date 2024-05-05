package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka.events;

import java.time.LocalDateTime;
import java.util.List;

public record VkPostData(List<String> imagesLinkList, LocalDateTime publishedAt, String text) {
}
