package com.alert.mechanism.exception;

public class AlertFailedException extends RuntimeException{
    private String errorMessage;

    public AlertFailedException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
