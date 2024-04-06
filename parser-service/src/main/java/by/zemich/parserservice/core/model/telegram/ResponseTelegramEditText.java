package by.zemich.parserservice.core.model.telegram;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class ResponseTelegramEditText extends ResponseTelegram{
    private EditMessageText text;
    private AnswerCallbackQuery answerCallbackQuery;

    public ResponseTelegramEditText(EditMessageText text, AnswerCallbackQuery answerCallbackQuery) {
        this.text = text;
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public EditMessageText getText() {
        return text;
    }

    public void setText(EditMessageText text) {
        this.text = text;
    }

    public AnswerCallbackQuery getAnswerCallbackQuery() {
        return answerCallbackQuery;
    }

    public void setAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        this.answerCallbackQuery = answerCallbackQuery;
    }
}
