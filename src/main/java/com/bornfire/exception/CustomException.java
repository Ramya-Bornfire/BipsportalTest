package com.bornfire.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus status;
    private final String message;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
