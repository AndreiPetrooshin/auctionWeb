package com.petrushin.builder.exceptions;

public class UserBetBuilderException extends AbstractBuilderException {

    public UserBetBuilderException() {
    }

    public UserBetBuilderException(String message) {
        super(message);
    }

    public UserBetBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
