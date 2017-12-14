package com.petrushin.builder.exceptions;

public class UserShippingAddressBuilderException extends AbstractBuilderException {

    public UserShippingAddressBuilderException() {
    }

    public UserShippingAddressBuilderException(String message) {
        super(message);
    }

    public UserShippingAddressBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
