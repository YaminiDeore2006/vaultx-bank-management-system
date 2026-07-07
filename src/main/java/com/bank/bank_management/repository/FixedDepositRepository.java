package com.bank.bank_management.repository;

import com.bank.bank_management.entity.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, Long> {

    List<FixedDeposit> findByStatus(String status);

    List<FixedDeposit> findByCustomer_Id(Long customerId);

    boolean existsByCustomer_IdAndStatus(Long customerId, String status);
}