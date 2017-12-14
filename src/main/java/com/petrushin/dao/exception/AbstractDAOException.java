package com.petrushin.dao.exception;

public class AbstractDAOException extends Exception {

    public AbstractDAOException() {
    }

    public AbstractDAOException(String message) {
        super(message);
    }

    public AbstractDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
