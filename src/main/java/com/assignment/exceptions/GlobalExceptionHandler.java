package com.assignment.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	 }
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 @ExceptionHandler(RuntimeException.class)
	 public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
	     return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 @ExceptionHandler(DataIntegrityViolationException.class)
	 public final ResponseEntity<RuntimeException> handleAllExceptionsIntegrity(RuntimeException ex) {
	     return new ResponseEntity<RuntimeException>(new RuntimeException("Foreign Key constraints violated"), HttpStatus.EXPECTATION_FAILED);
	 }
}
