package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.*;
import by.zemich.parserservice.dao.entity.ProductCardEntity;

import java.util.List;
import java.util.UUID;

public interface ProductCardService {
    ProductCardEntity save(ProductCardDto productCard);
    List<ProductCardEntity> saveAll(List<VkPostDto> vkPosts);
    List<ProductCardEntity> getByUUID(SupplierDto supplierDto);
    List<ProductCardEntity> getAll();
    List<ProductCardEntity> getAllBySupplier(SupplierDto supplierDto);
    void deleteByUUID(UUID postUuid);
    void deleteAllFromList(List<ProductCardDto> uuids);

}
