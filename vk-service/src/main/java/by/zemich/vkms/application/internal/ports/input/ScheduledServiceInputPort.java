package by.zemich.vkms.application.internal.ports.input;

import by.zemich.vkms.application.internal.commandservices.VkPostCommandService;
import by.zemich.vkms.application.internal.ports.output.SupplierClientOutputPort;
import by.zemich.vkms.application.internal.ports.output.VkClientOutputPort;
import by.zemich.vkms.application.internal.usecases.ScheduledServiceUseCase;
import by.zemich.vkms.domain.model.queries.FetchVKPostQuery;

import java.util.Collection;

public class ScheduledServiceInputPort implements ScheduledServiceUseCase {

    private final SupplierClientOutputPort supplierClientOutputPort;
    private final VkClientOutputPort vkClientOutputPort;
    private final VkPostCommandService vkPostCommandService;

    public ScheduledServiceInputPort(SupplierClientOutputPort supplierClientOutputPort,
                                     VkClientOutputPort vkClientOutputPort,
                                     VkPostCommandService vkPostCommandService) {
        this.supplierClientOutputPort = supplierClientOutputPort;
        this.vkClientOutputPort = vkClientOutputPort;
        this.vkPostCommandService = vkPostCommandService;
    }

    @Override
    public void fetchPosts() {
        supplierClientOutputPort.fetchSuppliersIds().stream()
                .map(supplierId -> vkClientOutputPort.fetchPost(
                        FetchVKPostQuery.builder()
                                .supplierVkId(supplierId.getSupplierVkId())
                                .supplierUUID(supplierId.getSupplierUUID())
                                .build()))
                .flatMap(Collection::stream)
                .forEach(vkPostCommandService::createPost);
    }
}
