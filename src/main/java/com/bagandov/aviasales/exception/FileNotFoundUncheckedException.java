package com.bagandov.aviasales.exception;

public class FileNotFoundUncheckedException extends RuntimeException {

    public FileNotFoundUncheckedException(String message) {
        super(message);
    }

    public FileNotFoundUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
