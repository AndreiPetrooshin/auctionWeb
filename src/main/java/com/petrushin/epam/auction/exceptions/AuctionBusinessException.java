package com.petrushin.epam.auction.exceptions;

/**
 * Main exception for our Application.
 */
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
