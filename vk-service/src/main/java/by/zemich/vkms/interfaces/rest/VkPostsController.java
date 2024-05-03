package by.zemich.vkms.interfaces.rest;

import by.zemich.vkms.application.VkApplicationService;
import by.zemich.vkms.application.queryservices.VkPostQueryService;
import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.aggregates.VkPostIdBKey;
import by.zemich.vkms.interfaces.rest.dto.VkPostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/vk")
public class VkPostsController {
    private final VkPostQueryService queryService;
    private final ModelMapper modelMapper;

    // TODO удалить
    private final VkApplicationService vkApplicationService;

    public VkPostsController(VkPostQueryService queryService, ModelMapper modelMapper, VkApplicationService vkApplicationService) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.vkApplicationService = vkApplicationService;
    }

    @RequestMapping("/v1/api/{id}")
    ResponseEntity<VkPostResponse> getByVkPostId(Integer postId, Integer ownerId){
        VkPost vkPost = queryService.findByPostBKey(new VkPostIdBKey(postId, ownerId));
        if (Objects.isNull(vkPost)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(vkPost, VkPostResponse.class));
    }

    @RequestMapping("/v1/api/{uuid}")
    ResponseEntity<VkPostResponse> getByUuid(@PathVariable UUID uuid){
        VkPost vkPost = queryService.find(uuid);
        if (Objects.isNull(vkPost)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(vkPost, VkPostResponse.class));
    }

    @RequestMapping("/v1/api/start")
    ResponseEntity<Void> getByUuid(){
        vkApplicationService.checkNewPosts();
        return ResponseEntity.ok().build();
    }

}
