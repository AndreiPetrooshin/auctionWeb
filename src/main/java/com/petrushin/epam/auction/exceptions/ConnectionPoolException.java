package com.petrushin.epam.auction.exceptions;

public class ConnectionPoolException extends AuctionBusinessException {

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
