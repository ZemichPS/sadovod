package by.zemich.parserservice.core.model.telegram;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;

import java.util.Objects;

public class ResponseTelegramAnswerCallBack extends ResponseTelegram {
    private AnswerCallbackQuery answerCallbackQuery;

    public ResponseTelegramAnswerCallBack(AnswerCallbackQuery answerCallbackQuery) {
        this.answerCallbackQuery = answerCallbackQuery;
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
        ResponseTelegramAnswerCallBack that = (ResponseTelegramAnswerCallBack) o;
        return Objects.equals(answerCallbackQuery, that.answerCallbackQuery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerCallbackQuery);
    }
}
