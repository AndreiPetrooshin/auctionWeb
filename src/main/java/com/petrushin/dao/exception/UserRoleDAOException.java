package com.petrushin.dao.exception;

public class UserRoleDAOException extends AbstractDAOException {

    public UserRoleDAOException() {
    }

    public UserRoleDAOException(String message) {
        super(message);
    }

    public UserRoleDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
