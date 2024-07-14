package by.zemich.cataloguems.catalogueservice.domain.exception;

public class PersistenceException extends  RuntimeException{
    public PersistenceException(String message) {
        super(message);
    }
}
