package com.mzupancic.usermanagement.exception;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(final String message) {
        super(message);
    }
}
