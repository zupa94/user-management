package com.mzupancic.usermanagement.exception;

public class PrivilegeDoesNotExistException extends RuntimeException {
    public PrivilegeDoesNotExistException(String message){
        super(message);
    }
}
