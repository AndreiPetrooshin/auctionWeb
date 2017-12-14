package com.petrushin.dao.exception;

public class UserDAOException extends AbstractDAOException {

    public UserDAOException() {
    }

    public UserDAOException(String message) {
        super(message);
    }

    public UserDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
