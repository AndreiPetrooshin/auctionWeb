package com.petrushin.builder.exceptions;

public class UserRoleBuilderException extends AbstractBuilderException {

    public UserRoleBuilderException() {
    }

    public UserRoleBuilderException(String message) {
        super(message);
    }

    public UserRoleBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
