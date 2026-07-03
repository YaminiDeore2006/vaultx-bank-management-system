package com.bank.bank_management.controller;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions()
                .stream()
                .map(transactionService::mapToDTO)
                .toList();
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionDTO> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactions(accountId)
                .stream()
                .map(transactionService::mapToDTO)
                .toList();
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDTO dto) {
        return ResponseEntity.ok("Transaction created");
    }
}