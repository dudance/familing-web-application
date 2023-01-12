package com.project.project.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
