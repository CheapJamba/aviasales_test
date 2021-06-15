package com.bagandov.aviasales.exception;

import lombok.Getter;

public class CSVFormatException extends RuntimeException {

    @Getter
    private String filePath;

    @Getter
    private String pattern;

    public CSVFormatException(String filePath, String pattern, String message, Throwable cause) {
        super(message, cause);
        this.filePath = filePath;
        this.pattern = pattern;
    }

    public CSVFormatException (String filePath, String pattern, Throwable cause) {
        this(filePath, pattern, "Строки в файле " + filePath + " должны соответствовать формату: (" + pattern + ")", cause);
    }

    public CSVFormatException(String filePath, String pattern, String message) {
        super(message);
        this.filePath = filePath;
        this.pattern = pattern;
    }

    public CSVFormatException (String filePath, String pattern) {
        this(filePath, pattern, "Строки в файле " + filePath + " должны соответствовать формату: (" + pattern + ")");
    }
}
