package com.finzly.loanApplicationManagement.errorHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;

import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.finzly.loanApplicationManagement.response.ErrorResponse;
@RestControllerAdvice
public class ErrorHandlerService {
	   
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public HashMap<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
	    	 HashMap<String, String> errors = new HashMap<>();
	    	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	    	        String fieldName = ((FieldError) error).getField();
	    	        String errorMessage = error.getDefaultMessage();
	    	        errors.put(fieldName, errorMessage);
	    	    });
	    	    return errors;
	    }
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(NoSuchElementException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidArgument(NoSuchElementException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), 500);
	    	    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(UnexpectedTypeException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidArgument(UnexpectedTypeException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), 500);
	    	    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidArgument(CustomerNotFoundException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), 500);
	    	    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(EmptyListException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidArgument(EmptyListException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), 200);
	    	    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(InvalidFormatException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidArgument(InvalidFormatException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), 500);
	    	    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentTypeMismatchException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), 500);
	    	    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }
	    
	    
}
