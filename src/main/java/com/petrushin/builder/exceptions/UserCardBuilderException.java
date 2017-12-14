package com.petrushin.builder.exceptions;

public class UserCardBuilderException extends AbstractBuilderException {

    public UserCardBuilderException() {
    }

    public UserCardBuilderException(String message) {
        super(message);
    }

    public UserCardBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
