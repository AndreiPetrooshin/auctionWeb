package com.petrushin.exceptions;

public class MD5EncodingServiceException extends Exception {


    public MD5EncodingServiceException() {
    }

    public MD5EncodingServiceException(String message) {
        super(message);
    }

    public MD5EncodingServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
