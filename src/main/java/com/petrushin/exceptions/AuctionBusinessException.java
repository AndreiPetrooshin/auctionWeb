package com.petrushin.exceptions;

public class AuctionBusinessException extends Exception {

    public AuctionBusinessException() {
    }

    public AuctionBusinessException(String message) {
        super(message);
    }

    public AuctionBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
