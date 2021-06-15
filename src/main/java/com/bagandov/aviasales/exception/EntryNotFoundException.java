package com.bagandov.aviasales.exception;

public class EntryNotFoundException extends RuntimeException {

    public EntryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntryNotFoundException(String message) {
        super(message);
    }
}
