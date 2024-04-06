package by.zemich.vkservice.infrastructure.adapters.output.postgresql;

import by.zemich.vkservice.application.model.VkPostDto;
import by.zemich.vkservice.domain.exception.VkPostNotFountException;
import by.zemich.vkservice.domain.model.vkpost.VkPost;
import by.zemich.vkservice.infrastructure.adapters.output.postgresql.repository.VkPostRepository;
import by.zemich.vkservice.infrastructure.adapters.output.postgresql.entity.VkPostEntity;
import by.zemich.vkservice.application.ports.output.PostPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VKPostPersistenceAdapter implements PostPersistencePort {
    private final ModelMapper mapper;
    private final VkPostRepository repository;

    public VKPostPersistenceAdapter(ModelMapper mapper, VkPostRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public VkPost save(VkPost vkPost) {
        VkPostEntity vkPostEntity = mapper.map(vkPost, VkPostEntity.class);
        vkPostEntity.setUuid(UUID.randomUUID());
        VkPostEntity savedPost = repository.save(vkPostEntity);
        return mapper.map(savedPost, VkPost.class);
    }

    @Override
    public VkPost update(VkPost vkPost) {
        VkPostEntity vkPostEntity = mapper.map(vkPost, VkPostEntity.class);
        VkPostEntity savedPost = repository.save(vkPostEntity);
        return mapper.map(savedPost, VkPost.class);
    }

    @Override
    public VkPost getByUuid(UUID uuid) {
        return repository.findById(uuid)
                .map(entity -> mapper.map(entity, VkPost.class))
                .orElseThrow(() -> new VkPostNotFountException("Post with uuid: %s is nowhere to be found".formatted(uuid)));

    }

    @Override
    public List<VkPostDto> getAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, VkPostDto.class))
                .toList();
    }

    @Override
    public void delete(VkPost vkPost) {
        VkPostEntity vkPostEntity = mapper.map(vkPost, VkPostEntity.class);
        repository.delete(vkPostEntity);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        if (!repository.existsById(uuid)) throw new VkPostNotFountException("Post with uuid: %s is nowhere to be found.");
        repository.deleteById(uuid);

    }
}
