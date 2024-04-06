package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.model.ProductCardDto;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.VkPostDto;
import by.zemich.parserservice.dao.api.ProductCardRepository;
import by.zemich.parserservice.dao.entity.ProductCardEntity;
import by.zemich.parserservice.service.api.ProductCardMaker;
import by.zemich.parserservice.service.api.ProductCardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCardServiceImpl implements ProductCardService {

    private final ProductCardRepository productCardRepository;
    private final ModelMapper modelMapper;

    public ProductCardServiceImpl(ProductCardRepository productCardRepository, ModelMapper modelMapper) {
        this.productCardRepository = productCardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductCardEntity save(ProductCardDto productCard) {
        ProductCardEntity productCardEntity = modelMapper.map(productCard, ProductCardEntity.class);
        return productCardRepository.save(productCardEntity);
    }


    @Override
    public List<ProductCardEntity> saveAll(List<VkPostDto> vkPosts) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCardEntity> getByUUID(SupplierDto supplierDto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCardEntity> getAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCardEntity> getAllBySupplier(SupplierDto supplierDto) {
        return null;
    }

    @Override
    public void deleteByUUID(UUID postUuid) {

    }

    @Override
    public void deleteAllFromList(List<ProductCardDto> uuids) {

    }


}
