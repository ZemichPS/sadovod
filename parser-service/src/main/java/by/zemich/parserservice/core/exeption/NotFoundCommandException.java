package by.zemich.parserservice.core.exeption;

import by.zemich.parserservice.core.enums.EActionType;

public class NotFoundCommandException extends RuntimeException {
    public NotFoundCommandException(EActionType actionType) {
        super(String.format("Command with action type %s is nowhere to be found", actionType));
    }
}
