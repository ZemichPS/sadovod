package by.zemich.parserservice.core.exeption;

import java.util.UUID;

public class NotFoundProductCardException extends RuntimeException {
    public NotFoundProductCardException(UUID productCardUuid) {
        super(String.format("Such product card doesnt found. UUID card : %S", productCardUuid));
    }
}
