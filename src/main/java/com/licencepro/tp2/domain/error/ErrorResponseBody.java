package com.licencepro.tp2.domain.error;

import java.time.LocalDateTime;

public class ErrorResponseBody {

    private LocalDateTime timestamp;
    private String message;
    private String classNameException;

    public ErrorResponseBody(LocalDateTime timestamp, String message, String classNameException) {
        this.timestamp = timestamp;
        this.message = message;
        this.classNameException = classNameException;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassNameException() {
        return classNameException;
    }

    public void setClassNameException(String classNameException) {
        this.classNameException = classNameException;
    }

    @Override
    public String toString() {
        return "ErrorResponseBody{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", classNameException='" + classNameException + '\'' +
                '}';
    }
}
