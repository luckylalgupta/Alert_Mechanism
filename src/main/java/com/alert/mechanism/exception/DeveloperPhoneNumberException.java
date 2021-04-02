package com.alert.mechanism.exception;

public class DeveloperPhoneNumberException extends RuntimeException{
    private String errorMessage;

    public DeveloperPhoneNumberException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
