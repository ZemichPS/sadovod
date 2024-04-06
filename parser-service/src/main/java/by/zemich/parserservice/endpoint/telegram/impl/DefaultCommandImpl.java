package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramJustMessage;
import by.zemich.parserservice.endpoint.telegram.api.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class DefaultCommandImpl implements Command {

    @Override
    public String getCommand() {
        return "defaultCommand";
    }

    @Override
    public EActionType getCommandType() {
        return EActionType.DEFAULT;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {
        String MESSAGE = "Необрабатываемая команда";
        SendMessage message = SendMessage.builder()
                .chatId(telegramUpdate.getMessage().getChatId().toString())
                .text(MESSAGE)
                .build();


        return new ResponseTelegramJustMessage(message);
    }
}
