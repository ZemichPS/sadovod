package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramJustMessage;
import by.zemich.parserservice.endpoint.telegram.api.CallBackAnswer;
import by.zemich.parserservice.endpoint.telegram.api.CallBackHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CallBackHandlerImpl implements CallBackHandler {

    private final Map<String, CallBackAnswer> callBackAnswerMap = new HashMap<>();

    @Override
    public void register(String answerName, CallBackAnswer callBackAnswer) {
        callBackAnswerMap.put(answerName, callBackAnswer);
    }

    @Override
    public ResponseTelegram handle(Update telegramUpdate) {
        String callback = telegramUpdate.getCallbackQuery().getData();
        String callBackPrefix = Arrays.stream(callback.split("->"))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Failed to recognize callback command"));

        return callBackAnswerMap.getOrDefault(callBackPrefix, new CallBackAnswer() {
            @Override
            public ResponseTelegram apply(Update telegramUpdate) {
                SendMessage sendMessage = SendMessage.builder()
                        .chatId(telegramUpdate.getCallbackQuery().getMessage().getChatId())
                        .text("Команда не распознана. I am so sorry...")
                        .build();
                return new ResponseTelegramJustMessage(sendMessage);
            }

            @Override
            public String getCallbackTrigger() {
                return null;
            }
        }).apply(telegramUpdate);
    }


}
