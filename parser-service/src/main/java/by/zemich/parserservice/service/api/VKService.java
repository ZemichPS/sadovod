package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.VKPostQuery;
import by.zemich.parserservice.core.model.VkPostDto;

import java.util.List;

public interface VKService {
    List<VkPostDto> getPosts(VKPostQuery query);
}
