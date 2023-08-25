package com.ltp.contacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(ContactNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleContactNotFoundException(ContactNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
