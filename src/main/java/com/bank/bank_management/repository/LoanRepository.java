package com.bank.bank_management.repository;

import com.bank.bank_management.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(String status);

    List<Loan> findByCustomer_Id(Long customerId);

    boolean existsByCustomer_IdAndStatus(Long customerId, String status);
}