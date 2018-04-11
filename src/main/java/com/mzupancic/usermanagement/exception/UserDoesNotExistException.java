package com.mzupancic.usermanagement.exception;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(final String message){
        super(message);
    }
}
