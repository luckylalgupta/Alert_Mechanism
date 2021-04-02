package com.alert.mechanism.exception;

public class DeveloperNameException extends RuntimeException{
    private String errorMessage;

    public DeveloperNameException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
