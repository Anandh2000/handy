package com.finzly.loanApplicationManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class LoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private double loanAmount;
	private LocalDate tradeDate;
	private LocalDate loanStartDate;
	private int termOfLoanInMonths;
	@Enumerated(EnumType.STRING)
	private PaymentTerm paymentTerm;
	private LocalDate  maturityDate;
	private int paymentFrequency;
	private double interestRate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentId",referencedColumnName = "customerId")
	public List<PaymentSchedule> paymentSchedules = new ArrayList<>();
	
	public LoanDetails(int customerId, double loanAmount, LocalDate tradeDate, LocalDate loanStartDate,
				int termOfLoanInMonths, PaymentTerm paymentTerm, LocalDate maturityDate, int paymentFrequency, double interestRate,
				List<PaymentSchedule> paymentSchedules) {
			super();
			this.customerId = customerId;
			this.loanAmount = loanAmount;
			this.tradeDate = tradeDate;
			this.loanStartDate = loanStartDate;
			this.termOfLoanInMonths = termOfLoanInMonths;
			this.paymentTerm = paymentTerm;
			this.maturityDate = maturityDate;
			this.paymentFrequency = paymentFrequency;
			this.interestRate = interestRate;
			this.paymentSchedules = paymentSchedules;
		}

	public PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	public List<PaymentSchedule> getPaymentSchedules() {
		return paymentSchedules;
	}

	public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
		this.paymentSchedules = paymentSchedules;
	}

	public int getTermOfLoanInMonths() {
		return termOfLoanInMonths;
	}

	public void setTermOfLoanInMonths(int termOfLoanInMonths) {
		this.termOfLoanInMonths = termOfLoanInMonths;
	}

	public LoanDetails() {
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDate getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(LocalDate loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public int getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(int paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}



	public double getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "LoanDetails [customerId=" + customerId + ", loanAmount=" + loanAmount + ", tradeDate=" + tradeDate
				+ ", loanStartDate=" + loanStartDate + ", termOfLoanInMonths=" + termOfLoanInMonths + ", paymentTerm="
				+ paymentTerm + ", maturityDate=" + maturityDate + ", paymentFrequency=" + paymentFrequency
				+ ", intrestRate=" + interestRate + ", paymentSchedules=" + paymentSchedules + "]";
	}
	
	
	
}
