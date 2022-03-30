package com.oportun.poc.testingstrategy.entrypoints.rest.handlers.error;

import com.oportun.poc.testingstrategy.domain.exceptions.BusinessException;
import com.oportun.poc.testingstrategy.domain.exceptions.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorMessage handleBusinessException(BusinessException businessException, WebRequest webRequest) {
        return getErrorMessage(HttpStatus.CONFLICT, businessException, webRequest);
    }

    @ExceptionHandler(value = {NoDataFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorMessage handleNoDataFoundException(NoDataFoundException noDataFoundException, WebRequest webRequest) {
        return getErrorMessage(HttpStatus.NOT_FOUND, noDataFoundException, webRequest);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorMessage handleGeneralException(Exception exception, WebRequest webRequest) {
        return getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception, webRequest);
    }

    private ErrorMessage getErrorMessage(HttpStatus httpStatus, Exception exception, WebRequest webRequest) {
        return new ErrorMessage(
                String.valueOf(httpStatus.value()),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
