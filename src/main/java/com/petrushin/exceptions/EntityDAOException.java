package com.petrushin.exceptions;

public class EntityDAOException extends AuctionBusinessException {

    public EntityDAOException() {
    }

    public EntityDAOException(String message) {
        super(message);
    }

    public EntityDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
