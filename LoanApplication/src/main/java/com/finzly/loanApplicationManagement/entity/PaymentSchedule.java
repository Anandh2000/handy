package com.finzly.loanApplicationManagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PaymentSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	private LocalDate paymentDate;
	private double principal;
	private double projectedInterest;
	@Enumerated(EnumType.STRING)
	private Status paymentStatus = Status.Projected;
	private double paymentAmount;
	
	public PaymentSchedule(LocalDate paymentDate, double principal, double projectedInterest, Status paymentStatus,
			double paymentAmount) {
		super();
		this.paymentDate = paymentDate;
		this.principal = principal;
		this.projectedInterest = projectedInterest;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
	}
	public PaymentSchedule() {
		super();
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getProjectedInterest() {
		return projectedInterest;
	}
	public void setProjectedInterest(double projectedInterest) {
		this.projectedInterest = projectedInterest;
	}
	public Status getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Status paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	@Override
	public String toString() {
		return "PaymentSchedule [paymentDate=" + paymentDate + ", principal=" + principal + ", projectedInterest="
				+ projectedInterest + ", paymentStatus=" + paymentStatus + ", paymentAmount=" + paymentAmount + "]";
	}
	

}