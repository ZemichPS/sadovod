package by.zemich.vkms.interfaces.rest;

import by.zemich.vkms.application.queryservices.VkPostQueryService;
import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.interfaces.rest.dto.VkPostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("v1/api")
public class VkPostsController {
    private final VkPostQueryService queryService;
    private final ModelMapper modelMapper;

    public VkPostsController(VkPostQueryService queryService, ModelMapper modelMapper) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/get/{id}")
    ResponseEntity<VkPostResponse> getByVkPostId(@PathVariable  Integer id){
        VkPost vkPost = queryService.findByPostId(id);
        if (Objects.isNull(vkPost)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(vkPost, VkPostResponse.class));
    }

    @RequestMapping("/get/{uuid}")
    ResponseEntity<VkPostResponse> getByUuid(@PathVariable UUID uuid){
        VkPost vkPost = queryService.find(uuid);
        if (Objects.isNull(vkPost)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(vkPost, VkPostResponse.class));
    }
}
