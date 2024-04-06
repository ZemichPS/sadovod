package by.zemich.supplierservice.domain.supplier.exception;

public class ParameterNotFoundException extends RuntimeException{
    public ParameterNotFoundException() {
    }

    public ParameterNotFoundException(String message) {
        super(message);
    }
}
