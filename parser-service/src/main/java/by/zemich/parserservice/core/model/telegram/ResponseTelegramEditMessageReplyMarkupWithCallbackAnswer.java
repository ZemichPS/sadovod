package by.zemich.parserservice.core.model.telegram;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

public class ResponseTelegramEditMessageReplyMarkupWithCallbackAnswer extends ResponseTelegram {
    private EditMessageReplyMarkup editMessageReplyMarkup;
    private AnswerCallbackQuery answerCallbackQuery;


    public ResponseTelegramEditMessageReplyMarkupWithCallbackAnswer(EditMessageReplyMarkup editMessageReplyMarkup, AnswerCallbackQuery answerCallbackQuery) {
        this.editMessageReplyMarkup = editMessageReplyMarkup;
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public EditMessageReplyMarkup getEditMessageReplyMarkup() {
        return editMessageReplyMarkup;
    }

    public void setEditMessageReplyMarkup(EditMessageReplyMarkup editMessageReplyMarkup) {
        this.editMessageReplyMarkup = editMessageReplyMarkup;
    }

    public AnswerCallbackQuery getAnswerCallbackQuery() {
        return answerCallbackQuery;
    }

    public void setAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        this.answerCallbackQuery = answerCallbackQuery;
    }
}
