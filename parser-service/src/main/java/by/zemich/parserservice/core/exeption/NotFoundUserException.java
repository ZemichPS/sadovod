package by.zemich.parserservice.core.exeption;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String userUuid) {
        super(String.format("User with id %s is nowhere to be found", userUuid));
    }
}
