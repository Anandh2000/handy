package com.finzly.loanApplicationManagement.errorHandler;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String message) {
		super(message);
	}

}
