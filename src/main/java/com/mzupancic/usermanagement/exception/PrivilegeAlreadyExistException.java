package com.mzupancic.usermanagement.exception;

public class PrivilegeAlreadyExistException extends RuntimeException {
    public PrivilegeAlreadyExistException(String message){
        super(message);
    }
}
