package com.petrushin.builder.exceptions;

public class AbstractBuilderException extends Exception {

    public AbstractBuilderException() {
    }

    public AbstractBuilderException(String message) {
        super(message);
    }

    public AbstractBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
