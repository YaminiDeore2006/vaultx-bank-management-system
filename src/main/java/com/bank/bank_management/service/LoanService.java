package com.bank.bank_management.service;

import com.bank.bank_management.dto.LoanDTO;
import com.bank.bank_management.entity.Customer;
import com.bank.bank_management.entity.Loan;
import com.bank.bank_management.repository.CustomerRepository;
import com.bank.bank_management.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // =========================
    // APPLY LOAN
    // =========================
    public Loan applyLoan(LoanDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Loan loan = new Loan();

        loan.setCustomer(customer);
        loan.setLoanType(dto.getLoanType());
        loan.setAmount(dto.getAmount());
        loan.setTenure(dto.getTenure());

        // Default Status
        loan.setStatus("PENDING");

        return loanRepository.save(loan);
    }

    // =========================
    // GET ALL LOANS
    // =========================
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // =========================
    // GET LOANS BY STATUS
    // =========================
    public List<Loan> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }

    // =========================
    // APPROVE LOAN
    // =========================
    public Loan approveLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus("APPROVED");

        return loanRepository.save(loan);
    }

    // =========================
    // REJECT LOAN
    // =========================
    public Loan rejectLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus("REJECTED");

        return loanRepository.save(loan);
    }

    // =========================
    // DTO Mapping
    // =========================
    public LoanDTO mapToDTO(Loan loan) {

        return new LoanDTO(
                loan.getId(),
                loan.getCustomer().getId(),
                loan.getLoanType(),
                loan.getAmount(),
                loan.getTenure(),
                loan.getStatus()
        );
    }
}