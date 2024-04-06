package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.model.TelegramSessionDto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

public interface TelegramSessionFacade {
    boolean checkForRegistration(String username);
    TelegramSessionDto create(Update telegramUpdate);
    TelegramSessionDto create(TelegramSessionDto telegramSessionDto);
    TelegramSessionDto create(Long telegramChatId, String username);
    Optional<TelegramSessionDto> getById(String username);
    List<TelegramSessionDto> getAll(String username);
    TelegramSessionDto update(TelegramSessionDto telegramSessionDto);
    TelegramSessionDto delete(TelegramSessionDto telegramSessionDto);



}
