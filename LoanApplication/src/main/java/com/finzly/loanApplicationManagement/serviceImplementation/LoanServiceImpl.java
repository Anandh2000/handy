package com.finzly.loanApplicationManagement.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import com.finzly.loanApplicationManagement.Service.LoanService;
import com.finzly.loanApplicationManagement.entity.LoanDetails;
import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
import com.finzly.loanApplicationManagement.entity.PaymentTerm;
import com.finzly.loanApplicationManagement.entity.Status;
import com.finzly.loanApplicationManagement.errorHandler.CustomerNotFoundException;
import com.finzly.loanApplicationManagement.errorHandler.EmptyListException;
import com.finzly.loanApplicationManagement.errorHandler.ErrorHandlerService;
import com.finzly.loanApplicationManagement.repository.LoanDetailsRepository;
import com.finzly.loanApplicationManagement.repository.PaymentScheduleRepository;
import com.finzly.loanApplicationManagement.response.SuccessResponse;

@Component
public class LoanServiceImpl implements LoanService {
	@Autowired
	private ErrorHandlerService errorHandlerService;

	@Autowired
	private LoanDetailsRepository repository;
	
	@Autowired
	private PaymentScheduleRepository repo;

	@Override
	public ResponseEntity<?> applyLoan(LoanDetails details) {
		details.setMaturityDate(details.getLoanStartDate().plusMonths(details.getTermOfLoanInMonths()));
		List<PaymentSchedule> schedules = details.getPaymentSchedules();
		int paymentSchedule = details.getTermOfLoanInMonths() / details.getPaymentFrequency();
		System.out.println(paymentSchedule);
		double tempLoanAmount = details.getLoanAmount();
		LocalDate tempPaymentDate = details.getLoanStartDate();
		for (int i = 0; i < paymentSchedule; i++) {
			PaymentSchedule schedule = new PaymentSchedule();
			tempPaymentDate = tempPaymentDate.plusMonths(details.getPaymentFrequency());
			schedule.setPaymentDate(tempPaymentDate);
			if (details.getPaymentTerm().equals(PaymentTerm.EvenPrincipal)) {
				schedule.setPrincipal(details.getLoanAmount() / paymentSchedule);
				schedule.setProjectedInterest(tempLoanAmount / details.getInterestRate());
				tempLoanAmount = tempLoanAmount - schedule.getPrincipal();
				schedule.setPaymentAmount(schedule.getProjectedInterest() + schedule.getPrincipal());
			} else {
				if(schedule.getPaymentDate().equals(details.getMaturityDate())){
					schedule.setPrincipal(details.getLoanAmount());
				}
				else {
					schedule.setPrincipal(0);
				}
				schedule.setProjectedInterest(
						((details.getLoanAmount()*details.getInterestRate())/100)/12);
				double paymentAmount = (schedule.getPaymentDate().equals(details.getMaturityDate()))
						? schedule.getProjectedInterest() + details.getLoanAmount()
						: schedule.getProjectedInterest();
				schedule.setPaymentAmount(paymentAmount);
			}
			schedules.add(schedule);
		}
		details.setPaymentSchedules(schedules);
		System.out.println(schedules.toString());
		repository.save(details);
		
	    SuccessResponse successResponse = new SuccessResponse("created",null , 200);
		return new ResponseEntity<>("created", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<LoanDetails>> getAllDetails() {
		if(repository.findAll().isEmpty()) {
			throw new EmptyListException("List is Empty"); 
		}
		return new ResponseEntity<List<LoanDetails>>(repository.findAll(), HttpStatus.OK);

	}
	
	@Override
	public ResponseEntity<List<PaymentSchedule>> getLoansByCustomerId(int id) {
	 LoanDetails  loanDetail= repository.findById(id)
			 .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found:"+id));
		for(PaymentSchedule schedule : loanDetail.getPaymentSchedules()) {
			if (schedule.getPaymentDate().compareTo(LocalDate.now()) < 0 || schedule.getPaymentStatus().equals(Status.AwaitingPayment)) {
				schedule.setPaymentStatus(Status.Paid);
			} else if (schedule.getPaymentDate().equals(LocalDate.now()) &&  !schedule.getPaymentStatus().equals(Status.Paid)) {
				schedule.setPaymentStatus(Status.AwaitingPayment);
			}
			repo.save(schedule);
		}
		return new ResponseEntity<List<PaymentSchedule>>(loanDetail.getPaymentSchedules(), HttpStatus.ACCEPTED);
	}
	
	
	@Override
	public ResponseEntity<?> ScheduleOfACustomer(int id) {
		return new ResponseEntity<>(repository.findById(id), HttpStatus.ACCEPTED);
	}
	
	

	@Override
	public ResponseEntity<?> payementScheduler() {
		return null;
	}

	@Override
	public ResponseEntity<?> setInterest(double rate) {
		// TODO Auto-generated method stub
		return null;
	}

}
