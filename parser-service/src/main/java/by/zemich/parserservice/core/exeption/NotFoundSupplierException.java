package by.zemich.parserservice.core.exeption;

public class NotFoundSupplierException extends RuntimeException {
    public NotFoundSupplierException(String supplierUuid) {
        super(String.format("Supplier with id %s is nowhere to be found", supplierUuid));
    }
}
