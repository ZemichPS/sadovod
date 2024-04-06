package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.endpoint.telegram.api.CallBackAnswer;
import by.zemich.parserservice.endpoint.telegram.api.CommandHandler;
import by.zemich.parserservice.service.api.TelegramSessionFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Log4j2
public class NextPostsCallBackAnswerImpl implements CallBackAnswer {
    private final CommandHandler commandHandler;
    private final TelegramSessionFacade sessionFacade;

    public NextPostsCallBackAnswerImpl(CommandHandler commandHandler, TelegramSessionFacade sessionFacade) {
        this.commandHandler = commandHandler;
        this.sessionFacade = sessionFacade;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {
        String telegramUserId = telegramUpdate.getCallbackQuery().getFrom().getUserName();
        TelegramSessionDto currentUserSession = sessionFacade.getById(telegramUserId).get();

        int newOffset = currentUserSession.getPostOffset() + currentUserSession.getPostCount();
        currentUserSession.setPostOffset(newOffset);
        sessionFacade.update(currentUserSession);

        return commandHandler.getByActionType(EActionType.GET_CARD).apply(telegramUpdate);
    }

    @Override
    public String getCallbackTrigger() {
        return "NEXT_POSTS";
    }
}
