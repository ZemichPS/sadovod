package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.config.property.TelegramProperties;
import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.core.model.telegram.*;
import by.zemich.parserservice.endpoint.telegram.api.CallBackHandler;
import by.zemich.parserservice.endpoint.telegram.api.CommandHandler;
import by.zemich.parserservice.service.api.TelegramSessionFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramProperties telegramProperties;
    private final CommandHandler commandHandler;
    private final CallBackHandler callBackHandler;
    private final TelegramSessionFacade sessionFacade;

    public TelegramBot(TelegramProperties telegramProperties,
                       CommandHandler commandHandler,
                       CallBackHandler callBackHandler,
                       TelegramSessionFacade sessionFacade) {
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
        this.commandHandler = commandHandler;
        this.callBackHandler = callBackHandler;
        this.sessionFacade = sessionFacade;

    }

    @Override
    public String getBotUsername() {
        return telegramProperties.getName();
    }


    @Override
    public void onUpdateReceived(Update update) {
        ResponseTelegram response = null;
        if (update.getMessage() != null) {
            if (update.getMessage().isCommand()) {
                response = commandHandler.handleCommands(update);
            }
        } else if (update.hasCallbackQuery()) {
            response = callBackHandler.handle(update);
        }

        applyTelegramResponse(response);
    }

    private void applyTelegramResponse(ResponseTelegram responseTelegram) {


        switch (responseTelegram) {
            case ResponseTelegramProductPostList telegramCard -> {

                String telegramUserId = telegramCard.getTelegramUserId();

                TelegramSessionDto currentSession = sessionFacade.getById(telegramUserId).orElseGet(
                        () -> {
                            TelegramSessionDto newSession = new TelegramSessionDto();
                            newSession.setUserName(telegramUserId);
                            return sessionFacade.create(newSession);
                        }
                );

                telegramCard.getProductCardTelegramDtoList().forEach(
                        productCardTelegramDto -> {
                            if (!productCardTelegramDto.getSendPhotoList().isEmpty() | productCardTelegramDto.getSendPhotoList() == null) {
                                productCardTelegramDto.getSendPhotoList().forEach(this::sendPhotoOrElseSkip);
                            }
                            sendMessageOrElseThrow(productCardTelegramDto.getSendMessage());
                            currentSession.addProductCar(productCardTelegramDto.getProductCard());
                            sessionFacade.update(currentSession);
                        }
                );
                sendMessageOrElseThrow(telegramCard.getNextPostsMessage());
            }
            
            case ResponseTelegramJustMessage justMessage->{
                SendMessage sendMessage = justMessage.getMessage();
                sendMessageOrElseThrow(sendMessage);
            }
            
            case ResponseTelegramEditText editText->{
                EditMessageText editMessageText = editText.getText();
                sendEditedMessageOrElseThrow(editMessageText);
            }
            
            case ResponseTelegramAnswerCallBack callBack->{
                AnswerCallbackQuery answerCallbackQuery = callBack.getAnswerCallbackQuery();
                closeCallbackOrElseThrow(answerCallbackQuery);
            }

            case ResponseTelegramTextAndCallback queryAndText->{
                closeCallbackOrElseThrow(queryAndText.getAnswerCallbackQuery());
                sendMessageOrElseThrow(queryAndText.getSendMessage());
            }
            default -> throw new IllegalStateException("Unexpected value: " + responseTelegram);
        }
      
    }


    private void sendPhotoOrElseSkip(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            //  throw new RuntimeException(e);
        }
    }

    private void sendMessageOrElseThrow(SendMessage sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private void sendEditedMessageOrElseThrow(EditMessageText sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void closeCallbackOrElseThrow(AnswerCallbackQuery answerCallbackQuery) {
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
