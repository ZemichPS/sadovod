package by.zemich.vkservice.infrastructure.adapters.output.postgresql.repository;

import by.zemich.vkservice.infrastructure.adapters.output.postgresql.entity.VkPostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VkPostRepository extends CrudRepository<VkPostEntity, UUID> {
    List<VkPostEntity> findAll();


}
