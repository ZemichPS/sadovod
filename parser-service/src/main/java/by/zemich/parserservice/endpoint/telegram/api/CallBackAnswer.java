package by.zemich.parserservice.endpoint.telegram.api;

import by.zemich.parserservice.core.model.TelegramContentDto;
import by.zemich.parserservice.core.model.UpdateDto;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallBackAnswer {
    ResponseTelegram apply(Update telegramUpdate);

    String getCallbackTrigger();
    @Autowired
    default void regMe(CallBackHandler handler){
        handler.register(getCallbackTrigger(), this);
    }
}
