package by.zemich.vkms.infrastructure.output.repositories.jpa;

import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.VkPostEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VkPostRepository extends JpaRepository<VkPostEntity, UUID> {

    @Override
    @Nonnull
    List<VkPostEntity> findAll();

    Optional<VkPostEntity> findByUuid(UUID uuid);

    boolean existsByHash(String hash);


}
