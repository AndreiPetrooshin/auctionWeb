package com.petrushin.builder.exceptions;

public class UserBuilderException extends AbstractBuilderException {

    public UserBuilderException() {
    }

    public UserBuilderException(String message) {
        super(message);
    }

    public UserBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
