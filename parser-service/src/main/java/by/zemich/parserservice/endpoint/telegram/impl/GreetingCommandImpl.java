package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramJustMessage;
import by.zemich.parserservice.endpoint.telegram.api.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class GreetingCommandImpl implements Command {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public EActionType getCommandType() {
        return EActionType.GREETING;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {
        String userName = telegramUpdate.getMessage().getFrom().getUserName();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(telegramUpdate.getMessage().getChatId().toString())
                .text(String.format("Приветствую Вас, %s. Для начала работы пройдите регистрацию. Выберите команду /sign_up из меню.", userName))
                .build();

        return new ResponseTelegramJustMessage(sendMessage);
    }
}
