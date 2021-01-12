package com.licencepro.tp2.exception.book;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            BookNotFoundException.class,
            BookDeletionException.class,
            BookInsertException.class
    })
    protected ResponseEntity<Object> handleBookNotFoundException(
            ResponseException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ex.getErrorResponseBody(),
                new HttpHeaders(),
                ex.getHttpStatus(),
                request);
    }
}
