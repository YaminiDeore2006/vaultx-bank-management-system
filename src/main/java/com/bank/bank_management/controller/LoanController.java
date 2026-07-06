package com.bank.bank_management.controller;

import com.bank.bank_management.dto.LoanDTO;
import com.bank.bank_management.entity.Loan;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.LoanService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // =========================
    // APPLY LOAN
    // =========================
    @PostMapping
    public ApiResponse applyLoan(@Valid @RequestBody LoanDTO dto) {

        Loan loan = loanService.applyLoan(dto);

        return new ApiResponse(
                "Loan applied successfully",
                loan,
                200
        );
    }

    // =========================
    // GET ALL LOANS
    // =========================
    @GetMapping
    public ApiResponse getAllLoans() {

        List<LoanDTO> loans = loanService.getAllLoans()
                .stream()
                .map(loanService::mapToDTO)
                .toList();

        return new ApiResponse(
                "Loans fetched successfully",
                loans,
                200
        );
    }

    // =========================
    // GET LOANS BY STATUS
    // =========================
    @GetMapping("/status/{status}")
    public ApiResponse getLoansByStatus(@PathVariable String status) {

        List<LoanDTO> loans = loanService.getLoansByStatus(status)
                .stream()
                .map(loanService::mapToDTO)
                .toList();

        return new ApiResponse(
                "Loans fetched successfully",
                loans,
                200
        );
    }

    // =========================
    // APPROVE LOAN
    // =========================
    @PutMapping("/{id}/approve")
    public ApiResponse approveLoan(@PathVariable Long id) {

        Loan loan = loanService.approveLoan(id);

        return new ApiResponse(
                "Loan approved successfully",
                loan,
                200
        );
    }

    // =========================
    // REJECT LOAN
    // =========================
    @PutMapping("/{id}/reject")
    public ApiResponse rejectLoan(@PathVariable Long id) {

        Loan loan = loanService.rejectLoan(id);

        return new ApiResponse(
                "Loan rejected successfully",
                loan,
                200
        );
    }
}