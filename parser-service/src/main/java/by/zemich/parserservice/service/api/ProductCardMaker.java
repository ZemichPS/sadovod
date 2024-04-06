package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.ProductCardDto;

public interface ProductCardMaker<T> {
    ProductCardDto create(T source);

}
