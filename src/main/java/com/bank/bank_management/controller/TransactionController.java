package com.bank.bank_management.controller;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // =========================
    // CREATE TRANSACTION
    // =========================
    @PostMapping
    public ApiResponse createTransaction(@Valid @RequestBody TransactionDTO dto) {

        Transaction transaction = transactionService.createTransaction(dto);

        return new ApiResponse(
                "Transaction created successfully",
                transaction,
                200
        );
    }

    // =========================
    // GET ALL TRANSACTIONS
    // =========================
    @GetMapping
    public ApiResponse getAllTransactions() {

        List<TransactionDTO> transactions =
                transactionService.getAllTransactions()
                        .stream()
                        .map(transactionService::mapToDTO)
                        .toList();

        return new ApiResponse(
                "All transactions fetched successfully",
                transactions,
                200
        );
    }

    // =========================
    // GET TRANSACTIONS BY ACCOUNT ID
    // =========================
    @GetMapping("/account/{accountId}")
    public ApiResponse getTransactionsByAccount(@PathVariable Long accountId) {

        List<TransactionDTO> transactions =
                transactionService.getTransactions(accountId)
                        .stream()
                        .map(transactionService::mapToDTO)
                        .toList();

        return new ApiResponse(
                "Account transactions fetched successfully",
                transactions,
                200
        );
    }
}