package by.zemich.parserservice.core.model.telegram;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;

public class ResponseTelegramTextAndCallback extends ResponseTelegram {
    private SendMessage sendMessage;
    private AnswerCallbackQuery answerCallbackQuery;

    public ResponseTelegramTextAndCallback(SendMessage sendMessage, AnswerCallbackQuery answerCallbackQuery) {
        this.sendMessage = sendMessage;
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public ResponseTelegramTextAndCallback() {
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public AnswerCallbackQuery getAnswerCallbackQuery() {
        return answerCallbackQuery;
    }

    public void setAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        this.answerCallbackQuery = answerCallbackQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseTelegramTextAndCallback that = (ResponseTelegramTextAndCallback) o;
        return Objects.equals(sendMessage, that.sendMessage) && Objects.equals(answerCallbackQuery, that.answerCallbackQuery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendMessage, answerCallbackQuery);
    }
}
