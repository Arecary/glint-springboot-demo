package com.sz.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  // handel EmailAlreadyExistsException
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);  // 返回 409 Conflict 状态码
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }


}
