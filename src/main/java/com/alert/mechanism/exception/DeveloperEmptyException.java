package com.alert.mechanism.exception;

public class DeveloperEmptyException extends RuntimeException {
    private String errorMessage;

    public DeveloperEmptyException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
