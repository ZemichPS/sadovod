package by.zemich.vkms.infrastructure.output.repositories;

import by.zemich.vkms.application.ports.output.VkPostManagementRepositoryOutputPort;
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

        SupplierEntity supplierEntity = supplierRepository.getReferenceById(supplierUuid);

        if (!existsById(supplierEntity)) {
            supplierEntity = SupplierEntity.createNewSupplier(supplier);
            supplierRepository.save(supplierEntity);
        }
        vkPostForSave.setSupplier(supplierEntity);

        vkPostRepository.save(vkPostForSave);
        return newVkPost;
    }

    @Override
    public boolean existsByHash(String hash) {
        return vkPostRepository.existsByHash(hash);
    }

    private boolean existsById(SupplierEntity supplier) {
        try {
            supplier.getName();
            return true;
            //TODO узнать какой метод выбрасывает JPA
        } catch (Exception e) {
            return false;
        }
    }

}
