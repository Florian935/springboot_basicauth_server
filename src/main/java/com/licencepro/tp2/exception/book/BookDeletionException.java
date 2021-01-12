package com.licencepro.tp2.exception.book;

import com.licencepro.tp2.domain.error.ErrorResponseBody;
import org.springframework.http.HttpStatus;

public class BookDeletionException extends ResponseException {

    public BookDeletionException(String message, HttpStatus status) {
        super(message, status);
    }

    @Override
    public ErrorResponseBody getErrorResponseBody() {
        return errorResponseBody;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
