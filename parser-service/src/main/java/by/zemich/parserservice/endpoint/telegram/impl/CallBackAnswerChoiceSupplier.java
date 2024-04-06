package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramAnswerCallBack;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramEditMessageReplyMarkupWithCallbackAnswer;
import by.zemich.parserservice.endpoint.telegram.api.CallBackAnswer;
import by.zemich.parserservice.service.api.TelegramSessionFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.UUID;

@Component
@Log4j2
public class CallBackAnswerChoiceSupplier implements CallBackAnswer {

    private final TelegramSessionFacade sessionFacade;

    public CallBackAnswerChoiceSupplier(TelegramSessionFacade sessionFacade) {
        this.sessionFacade = sessionFacade;
    }


    @Override
    public ResponseTelegram apply(Update telegramUpdate) {
        CallbackQuery callbackQuery = telegramUpdate.getCallbackQuery();

        String[] callBackArray = telegramUpdate.getCallbackQuery().getData().split("->");
        String supplierUuid = callBackArray[1];

        String telegramUserId = telegramUpdate.getCallbackQuery().getFrom().getUserName();

        TelegramSessionDto currentUserSession = sessionFacade.getById(telegramUserId)
               .orElseGet(() -> sessionFacade.create(callbackQuery.getMessage().getChatId(), telegramUserId));

        currentUserSession.setCurrentSupplierUuid(UUID.fromString(supplierUuid));
        currentUserSession.setPostOffset(0);
        sessionFacade.update(currentUserSession);

        AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
                .callbackQueryId(callbackQuery.getId())
                .build();

        return new ResponseTelegramAnswerCallBack(answerCallbackQuery);
    }

    @Override
    public String getCallbackTrigger() {
        return "supplier_uuid";
    }
}
