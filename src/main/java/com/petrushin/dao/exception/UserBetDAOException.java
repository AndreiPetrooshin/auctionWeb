package com.petrushin.dao.exception;

public class UserBetDAOException extends EntityDAOException {

    public UserBetDAOException() {
    }

    public UserBetDAOException(String message) {
        super(message);
    }

    public UserBetDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
