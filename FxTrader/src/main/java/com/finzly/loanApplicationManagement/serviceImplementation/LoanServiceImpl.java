package com.finzly.loanApplicationManagement.serviceImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.finzly.loanApplicationManagement.Service.LoanService;
import com.finzly.loanApplicationManagement.entity.LoanDetails;
import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
import com.finzly.loanApplicationManagement.entity.PaymentTerm;
import com.finzly.loanApplicationManagement.entity.Status;
import com.finzly.loanApplicationManagement.errorHandler.ErrorHandlerService;
import com.finzly.loanApplicationManagement.repository.LoanDetailsRepository;

@Component
public class LoanServiceImpl implements LoanService {
	@Autowired
	private ErrorHandlerService errorHandlerService;

	@Autowired
	private LoanDetailsRepository repository;

	@Override
	public ResponseEntity<?> applyLoan(LoanDetails details) {
		details.setMaturityDate(details.getLoanStartDate().plusMonths(details.getTermOfLoanInMonths()));
		List<PaymentSchedule> schedules = details.getPaymentSchedules();
		int paymentSchedule = details.getTermOfLoanInMonths() / details.getPaymentFrequency();
		int years = details.getTermOfLoanInMonths() / 12;
		System.out.println(paymentSchedule);
		double tempLoanAmount = details.getLoanAmount();
		LocalDate tempPaymentDate = details.getLoanStartDate();
		for (int i = 0; i < paymentSchedule; i++) {
			PaymentSchedule schedule = new PaymentSchedule();
			tempPaymentDate = tempPaymentDate.plusMonths(details.getPaymentFrequency());
			schedule.setPaymentDate(tempPaymentDate);
			if (details.getPaymentTerm().equals(PaymentTerm.EvenPrincipal)) {
				schedule.setPrincipal(details.getLoanAmount() / paymentSchedule);
				schedule.setProjectedInterest(tempLoanAmount / details.getIntrestRate());
				tempLoanAmount = tempLoanAmount - schedule.getPrincipal();
				schedule.setPaymentAmount(schedule.getProjectedInterest() + schedule.getPrincipal());
			} else {
				schedule.setPrincipal(0);
				schedule.setProjectedInterest(
						(details.getLoanAmount() / details.getIntrestRate()) / paymentSchedule * years);
				double paymentAmount = (schedule.getPaymentDate().equals(details.getMaturityDate()))
						? schedule.getProjectedInterest() + details.getLoanAmount()
						: schedule.getProjectedInterest();
				schedule.setPaymentAmount(paymentAmount);
			}
			if (schedule.getPaymentDate().equals(LocalDate.now())) {
				schedule.setPaymentStatus(Status.AwaitingPayment);
			} else if (schedule.getPaymentDate().compareTo(LocalDate.now()) < 0) {
				schedule.setPaymentStatus(Status.Paid);
			}
			schedules.add(schedule);
		}
		details.setPaymentSchedules(schedules);
		System.out.println(schedules.toString());
		repository.save(details);
		return new ResponseEntity<>("created", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllDetails() {
		return new ResponseEntity(repository.findAll(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getLoansByCustomerId(int id) {
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
