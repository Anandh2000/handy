package com.finzly.loanApplicationManagement.errorHandler;

public class EmptyListException extends RuntimeException {
	public EmptyListException(String message) {
		super(message);
	}
}
