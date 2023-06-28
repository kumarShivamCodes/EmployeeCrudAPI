package com.nexttuple.employee.exception;

import com.nexttuple.employee.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ResourceExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getReason());
      if(ex.getReason().equals("The requested URL is not valid."))
      {
         return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
   public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
      String message = "Invalid ID format.";
      String reason = "The provided ID does not match the required data type.";
      ErrorResponse errorResponse = new ErrorResponse(message, reason);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

}
