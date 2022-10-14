package com.finzly.loanApplicationManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long>{

}
