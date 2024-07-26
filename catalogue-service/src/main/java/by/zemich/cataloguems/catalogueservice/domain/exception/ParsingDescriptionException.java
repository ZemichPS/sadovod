package by.zemich.cataloguems.catalogueservice.domain.exception;

public class ParsingDescriptionException extends RuntimeException{
    public ParsingDescriptionException(String message) {
        super(message);
    }

    public ParsingDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
