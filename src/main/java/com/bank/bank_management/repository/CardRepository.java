package com.bank.bank_management.repository;

import com.bank.bank_management.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByStatus(String status);

    List<Card> findByCustomer_Id(Long customerId);

    boolean existsByCustomer_IdAndStatus(Long customerId, String status);
}