package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.exeption.NotFoundCommandException;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.endpoint.telegram.api.Command;
import by.zemich.parserservice.endpoint.telegram.api.CommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandHandlerImpl implements CommandHandler {

    private final Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    @Override
    public Command getByActionType(EActionType actionType) {
        return commandMap.values().stream()
                .filter(command -> command.getCommandType().equals(actionType))
                .findFirst().orElseThrow(()-> new NotFoundCommandException(actionType));
    }

    @Override
    public ResponseTelegram handleCommands(Update telegramUpdate) {
        String command = telegramUpdate.getMessage().getText().split(" ")[0];

        Command handler = commandMap.getOrDefault(command, getByActionType(EActionType.DEFAULT));
        return handler.apply(telegramUpdate);
    }


}
