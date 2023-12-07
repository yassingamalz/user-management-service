package com.logiclytics.usermanagementservice.exception;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String message) {
        super(message);

    }
}
