package by.zemich.parserservice.core.exeption;

import by.zemich.parserservice.core.enums.EActionType;

public class NotFoundSessionException extends RuntimeException {
    public NotFoundSessionException(String username) {
        super(String.format("Such telegram session doesn't exist. Telegram user id: %S", username));
    }
}
