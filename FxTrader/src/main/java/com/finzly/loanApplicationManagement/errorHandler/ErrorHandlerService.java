package com.finzly.loanApplicationManagement.errorHandler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.finzly.loanApplicationManagement.response.ErrorResponse;
@RestControllerAdvice
public class ErrorHandlerService {
	    public ErrorResponse inValidEntry(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }
	    public ErrorResponse emptySet(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }
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
	    
}
