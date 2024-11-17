package org.valentine.goldspoon.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.valentine.goldspoon.exception.BookAlreadyExists;
import org.valentine.goldspoon.exception.BookNotFound;
import org.valentine.goldspoon.exception.EmailAlreadyExists;
import org.valentine.goldspoon.exception.ReaderNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExists.class)
    public ResponseEntity<String> handleBookAlreadyExists(BookAlreadyExists ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReaderNotFound.class)
    public ResponseEntity<String> handleReaderNotFound(ReaderNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExists ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
