package com.petrushin.service.exception;

public class LoginCommandException extends CommandException {

    public LoginCommandException() {
    }

    public LoginCommandException(String message) {
        super(message);
    }

    public LoginCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
