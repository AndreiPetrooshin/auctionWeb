package com.petrushin.service.exception;

public class RegistrationCommandException extends CommandException {

    public RegistrationCommandException() {
    }

    public RegistrationCommandException(String message) {
        super(message);
    }

    public RegistrationCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
