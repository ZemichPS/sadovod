package by.zemich.parserservice.core.model.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ResponseTelegramJustMessage extends ResponseTelegram {
    private SendMessage message;

    public ResponseTelegramJustMessage(SendMessage message) {
        this.message = message;
    }

    public SendMessage getMessage() {
        return message;
    }

    public void setMessage(SendMessage message) {
        this.message = message;
    }


}
