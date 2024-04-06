package by.zemich.supplierservice.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    SUPPLIER_NOT_FOUND("ERR_SUPPLIER_001", "Student not found"),
    INVALID_SUPPLIER("ERR_SUPPLIER_001", "Supplier invalid"),
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error");



    private final String code;
    private final String Message;


    ErrorCatalog(String code, String message) {
        this.code = code;
        Message = message;
    }
}
