package by.zemich.vkms.application.ports.output;

import by.zemich.vkms.application.usecases.VkPostManagementUseCase;
import by.zemich.vkms.domain.model.aggregates.VkPost;

import java.util.List;

public interface FetchVkPostsOutputPort {
     List<VkPost> fetchPost(VkPostManagementUseCase.FetchVKPostQuery query);

}
