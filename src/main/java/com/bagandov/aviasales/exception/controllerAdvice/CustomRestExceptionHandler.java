package com.bagandov.aviasales.exception.controllerAdvice;

import com.bagandov.aviasales.exception.CSVFormatException;
import com.bagandov.aviasales.exception.EntryNotFoundException;
import com.bagandov.aviasales.exception.FileNotFoundUncheckedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CSVFormatException.class})
    public ResponseEntity<Object> handleCSVFormatException(CSVFormatException ex, WebRequest request) {
        String error = "Can't unmarshal object from file " + ex.getFilePath() + " - entries have wrong format.";
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({EntryNotFoundException.class})
    public ResponseEntity<Object> handleEntryNotFoundException(EntryNotFoundException ex, WebRequest request) {
        String error = "Couldn't find entry with requested parameters";
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({FileNotFoundUncheckedException.class})
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundUncheckedException ex, WebRequest request) {
        String error = "Haven't found a file at a specified path";
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleOther(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "error occurred");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}
