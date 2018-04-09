package com.mzupancic.usermanagement.exception;

public class UserDoNotExistException extends RuntimeException{
    public UserDoNotExistException(final String message){
        super(message);
    }
}
