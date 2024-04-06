package by.zemich.parserservice.endpoint.telegram.api;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    String getCommand();

    EActionType getCommandType();

    ResponseTelegram apply(Update telegramUpdate);
    @Autowired
    default void regMe(CommandHandler commandHandler){
        commandHandler.register(getCommand(), this);
    }

}
