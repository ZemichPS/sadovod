package by.zemich.vkservice.application.service;

import by.zemich.vkservice.application.model.VKPostQuery;
import by.zemich.vkservice.application.ports.input.SupplierInputPort;
import by.zemich.vkservice.application.ports.input.VKServicePort;
import by.zemich.vkservice.application.ports.output.PostAsynchronousTransferPort;
import by.zemich.vkservice.application.ports.output.PostPersistencePort;
import by.zemich.vkservice.domain.model.supplier.Supplier;
import by.zemich.vkservice.domain.model.vkpost.VkPost;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VkService {
    private final PostAsynchronousTransferPort postAsynchronousTransferPort;
    private final PostPersistencePort postPersistencePort;
    private final VKServicePort vkServicePort;

    private final SupplierInputPort supplierInputPort;

    public VkService(PostAsynchronousTransferPort postAsynchronousTransferPort,
                     PostPersistencePort postPersistencePort,
                     VKServicePort vkServicePort,
                     SupplierInputPort supplierInputPort) {
        this.postAsynchronousTransferPort = postAsynchronousTransferPort;
        this.postPersistencePort = postPersistencePort;
        this.vkServicePort = vkServicePort;
        this.supplierInputPort = supplierInputPort;
    }

    @Scheduled
    public void handlePost() {
        List<Supplier> supplierList = supplierInputPort.getAll();

        supplierList.forEach(supplier -> {
            VKPostQuery query = getVkPostQuery(supplier);
            List<VkPost> postList = vkServicePort.getPosts(query);

            List<VkPost> vkPostList = postList.stream()
                    .map(postPersistencePort::save)
                    .toList();

            postAsynchronousTransferPort.send(vkPostList);
        });
    }


    private VKPostQuery getVkPostQuery(Supplier supplier) {
        return VKPostQuery.builder()
                .interval(7)
                .count(20)
                .offset(0)
                .supplier(supplier)
                .build();
    }

}
