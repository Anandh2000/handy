package com.finzly.loanApplicationManagement.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finzly.loanApplicationManagement.entity.LoanDetails;
@Service
public interface LoanService {
	public ResponseEntity<?> applyLoan(LoanDetails details);
	public ResponseEntity<?> getAllDetails();
	public ResponseEntity<?> payementScheduler();
	public ResponseEntity<?> setInterest(double rate);
	public ResponseEntity<?> getLoansByCustomerId(int id);
	public ResponseEntity<?> ScheduleOfACustomer(int id);
}