package com.petrushin.exceptions;

public class AuctionBuisnessException extends Exception {

    public AuctionBuisnessException() {
    }

    public AuctionBuisnessException(String message) {
        super(message);
    }

    public AuctionBuisnessException(String message, Throwable cause) {
        super(message, cause);
    }
}
