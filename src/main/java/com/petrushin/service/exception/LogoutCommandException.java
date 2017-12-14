package com.petrushin.service.exception;

public class LogoutCommandException extends CommandException {

    public LogoutCommandException() {
    }

    public LogoutCommandException(String message) {
        super(message);
    }

    public LogoutCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
