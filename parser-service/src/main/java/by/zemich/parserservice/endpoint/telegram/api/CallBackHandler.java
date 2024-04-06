package by.zemich.parserservice.endpoint.telegram.api;

import by.zemich.parserservice.core.model.TelegramContentDto;
import by.zemich.parserservice.core.model.UpdateDto;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallBackHandler {
    void register(String answerName, CallBackAnswer callBackAnswer);

    ResponseTelegram handle(Update telegramUpdate);
}
