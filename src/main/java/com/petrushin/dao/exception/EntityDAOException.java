package com.petrushin.dao.exception;

public class EntityDAOException extends Exception {

    public EntityDAOException() {
    }

    public EntityDAOException(String message) {
        super(message);
    }

    public EntityDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
