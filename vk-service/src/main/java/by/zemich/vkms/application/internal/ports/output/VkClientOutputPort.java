package by.zemich.vkms.application.internal.ports.output;

import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.domain.model.queries.FetchVKPostQuery;

import java.util.List;

public interface VkClientOutputPort {
     List<CreateVkPostCommand> fetchPost(FetchVKPostQuery query);

}
