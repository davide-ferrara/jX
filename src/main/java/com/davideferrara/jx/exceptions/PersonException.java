package com.davideferrara.jx.exceptions;

public class PersonException extends RuntimeException {
    private String message;

    public PersonException() {
        super();
    }

    public PersonException(String message) {
        super(message);
    }

}
