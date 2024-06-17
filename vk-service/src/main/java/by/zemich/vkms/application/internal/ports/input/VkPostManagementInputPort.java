package by.zemich.vkms.application.internal.ports.input;

import by.zemich.vkms.application.internal.ports.output.FetchSuppliersOutputPort;
import by.zemich.vkms.application.internal.ports.output.FetchVkPostsOutputPort;
import by.zemich.vkms.application.internal.ports.output.PublishEventOutputPort;
import by.zemich.vkms.application.internal.ports.output.VkPostManagementRepositoryOutputPort;
import by.zemich.vkms.application.internal.usecases.VkPostManagementUseCase;
import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import by.zemich.vkms.domain.model.services.EventManagerService;
import java.util.Collection;

public class VkPostManagementInputPort implements VkPostManagementUseCase {

    private final FetchSuppliersOutputPort fetchSuppliersOutputPort;
    private final FetchVkPostsOutputPort fetchVkPostsOutputPort;
    private final PublishEventOutputPort publishEventOutputPort;
    private final VkPostManagementRepositoryOutputPort vkPostManagementRepositoryOutputPort;

    public VkPostManagementInputPort(FetchSuppliersOutputPort fetchSuppliersOutputPort,
                                     FetchVkPostsOutputPort fetchVkPostsOutputPort,
                                     PublishEventOutputPort publishEventOutputPort,
                                     VkPostManagementRepositoryOutputPort vkPostManagementRepositoryOutputPort) {
        this.fetchSuppliersOutputPort = fetchSuppliersOutputPort;
        this.fetchVkPostsOutputPort = fetchVkPostsOutputPort;
        this.publishEventOutputPort = publishEventOutputPort;
        this.vkPostManagementRepositoryOutputPort = vkPostManagementRepositoryOutputPort;
    }

    @Override
    public void fetchPostsFromVK() {
        EventManagerService eventManagerService = new EventManagerService();

        fetchSuppliersOutputPort.fetchSuppliers().stream()
                .map(supplier -> fetchVkPostsOutputPort.fetchPost(
                        FetchVKPostQuery.builder()
                                .supplierVkId(supplier.getVkId())
                                .supplierUUID(supplier.getUuid())
                                .supplierName(supplier.getName())
                                .count(50)
                                .interval(15)
                                .offset(0)
                                .build()))
                .flatMap(Collection::stream)
                .filter(post-> !vkPostManagementRepositoryOutputPort.existsByHash(post.getVkPostId().getHash()))
                .forEach(vkPost -> {
                    vkPostManagementRepositoryOutputPort.savePost(vkPost);
                    VkPostCreatedEvent vkPostCreatedEvent = eventManagerService.createdEvent(vkPost);
                    publishEventOutputPort.publish(vkPostCreatedEvent);
                });
    }
}
