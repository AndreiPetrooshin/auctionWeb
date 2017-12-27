package com.petrushin.exceptions;

public class CreatorException extends AuctionBusinessException {

    public CreatorException() {
    }

    public CreatorException(String message) {
        super(message);
    }

    public CreatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
