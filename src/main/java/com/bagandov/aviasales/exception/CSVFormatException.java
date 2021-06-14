package com.bagandov.aviasales.exception;

public class CSVFormatException extends RuntimeException {

    public CSVFormatException(String message, Exception cause) {
        super(message, cause);
    }

    public CSVFormatException(String message) {
        super(message);
    }
}
