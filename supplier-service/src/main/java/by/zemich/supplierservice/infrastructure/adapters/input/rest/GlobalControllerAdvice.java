package by.zemich.supplierservice.infrastructure.adapters.input.rest;

import by.zemich.supplierservice.domain.supplier.exception.SupplierNotFoundException;
import by.zemich.supplierservice.infrastructure.adapters.input.rest.model.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;

import static by.zemich.supplierservice.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SupplierNotFoundException.class)
    public ErrorResponse handleSupplierNotFoundException() {
        return ErrorResponse.builder()
                .code(SUPPLIER_NOT_FOUND.getCode())
                .message(SUPPLIER_NOT_FOUND.getMessage())
                .details(List.of())
                .timestamp(LocalDateTime.now())
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        assert bindingResult != null;
        return ErrorResponse.builder()
                .code(INVALID_SUPPLIER.getCode())
                .message(INVALID_SUPPLIER.getMessage())
                .details(bindingResult.getFieldErrors().stream().map(
                        DefaultMessageSourceResolvable::getDefaultMessage
                ).toList())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception){
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .timestamp(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .build();
    }

}
