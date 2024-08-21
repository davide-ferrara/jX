package com.davideferrara.jx.exceptions;

public class ProfileException extends RuntimeException {
    private String message;

    public ProfileException() {
        super();
    }

    public ProfileException(String message) {
        super(message);
    }

}
