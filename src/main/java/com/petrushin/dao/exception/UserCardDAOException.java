package com.petrushin.dao.exception;

public class UserCardDAOException extends EntityDAOException {

    public UserCardDAOException() {
    }

    public UserCardDAOException(String message) {
        super(message);
    }

    public UserCardDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
