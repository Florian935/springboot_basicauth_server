package com.licencepro.tp2.exception.book;

import com.licencepro.tp2.domain.error.ErrorResponseBody;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class ResponseException extends RuntimeException {

    protected final ErrorResponseBody errorResponseBody;
    protected final HttpStatus httpStatus;

    public ResponseException(String errMessage, HttpStatus status) {
        super(errMessage);
        this.errorResponseBody = new ErrorResponseBody(
                LocalDateTime.now(), errMessage, this.getClass().toString());
        this.httpStatus = status;
    }

    public abstract ErrorResponseBody getErrorResponseBody();

    public abstract HttpStatus getHttpStatus();
}
