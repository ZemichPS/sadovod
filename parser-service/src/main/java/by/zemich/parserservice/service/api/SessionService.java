package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.dao.entity.TelegramSessionHash;

import java.util.List;
import java.util.Optional;

public interface SessionService{
    TelegramSessionHash save(TelegramSessionDto session);
    List<TelegramSessionHash> getAll();
    Optional<TelegramSessionHash> getById(String username);
    TelegramSessionHash update(TelegramSessionDto session);
    void delete(TelegramSessionDto session);
    boolean existsById(String username);


}
