package com.davideferrara.jx.exceptions;

public class ProfilePostException extends RuntimeException {
    private String message;

    public ProfilePostException() {
        super();
    }

    public ProfilePostException(String message) {
        super(message);
    }

}
