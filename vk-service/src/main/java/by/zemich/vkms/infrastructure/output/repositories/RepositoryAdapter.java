package by.zemich.vkms.infrastructure.output.repositories;

import by.zemich.vkms.application.internal.ports.output.VkPostManagementRepositoryOutputPort;
import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.infrastructure.output.repositories.jpa.SupplierRepository;
import by.zemich.vkms.infrastructure.output.repositories.jpa.VkPostRepository;
import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.SupplierEntity;
import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.VkPostEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RepositoryAdapter implements VkPostManagementRepositoryOutputPort {
    private final Converter converter;
    private final SupplierRepository supplierRepository;
    private final VkPostRepository vkPostRepository;

    public RepositoryAdapter(Converter converter, SupplierRepository supplierRepository, VkPostRepository vkPostRepository) {
        this.converter = converter;
        this.supplierRepository = supplierRepository;
        this.vkPostRepository = vkPostRepository;
    }

    @Override
    public VkPost savePost(VkPost newVkPost) {
        VkPostEntity vkPostForSave = converter.convertWithoutSupplier(newVkPost);

        Supplier supplier = newVkPost.getSupplier();
        UUID supplierUuid = supplier.getUuid();

        supplierRepository.findById(supplierUuid).ifPresentOrElse(supplierEntity1 -> vkPostForSave.setSupplier(supplierEntity1),
                () -> {
                    SupplierEntity newSupplier = SupplierEntity.createNewSupplier(supplier);
                    supplierRepository.save(newSupplier);
                    vkPostForSave.setSupplier(newSupplier);

                }
        );
        vkPostRepository.save(vkPostForSave);
        return newVkPost;
    }

    @Override
    public boolean existsByHash(String hash) {
        return vkPostRepository.existsByHash(hash);
    }


}
