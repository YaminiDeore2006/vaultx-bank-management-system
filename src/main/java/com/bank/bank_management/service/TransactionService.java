package com.bank.bank_management.service;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.entity.Account;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.repository.AccountRepository;
import com.bank.bank_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // CREATE TRANSACTION (FIXED)
    public Transaction createTransaction(TransactionDTO dto) {

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction transaction = new Transaction();

        transaction.setAccount(account);
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setDate(LocalDateTime.now());

        double amount = dto.getAmount();

        if ("DEPOSIT".equalsIgnoreCase(dto.getType())) {
            account.setBalance(account.getBalance() + amount);
        }

        else if ("WITHDRAW".equalsIgnoreCase(dto.getType())) {

            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient Balance");
            }

            account.setBalance(account.getBalance() - amount);
        }

        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    // GET ALL
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // GET BY ACCOUNT
    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccount_Id(accountId);
    }

    // DTO mapping
    public TransactionDTO mapToDTO(Transaction transaction) {

        return new TransactionDTO(
                transaction.getId(),
                transaction.getAccount().getId(),   // ✅ ADD THIS
                transaction.getAmount(),
                transaction.getType()
        );
    }
}