package com.mzupancic.usermanagement.exception;

public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException(final String message) {
        super(message);
    }
}
