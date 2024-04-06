package by.zemich.parserservice.endpoint.telegram.api;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {
    void register(String commandName, Command command);

    Command getByActionType(EActionType actionType);

    ResponseTelegram handleCommands(Update telegramUpdate);

}
