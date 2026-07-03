package com.bank.bank_management.service;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.entity.Account;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.repository.AccountRepository;
import com.bank.bank_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Save Transaction + Update Balance
    public Transaction saveTransaction(Transaction transaction) {

        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double amount = transaction.getAmount();

        if ("DEPOSIT".equalsIgnoreCase(transaction.getType())) {
            account.setBalance(account.getBalance() + amount);
        }

        else if ("WITHDRAW".equalsIgnoreCase(transaction.getType())) {

            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient Balance");
            }

            account.setBalance(account.getBalance() - amount);
        }

        accountRepository.save(account);

        transaction.setAccount(account);

        if (transaction.getDate() == null) {
            transaction.setDate(java.time.LocalDateTime.now());
        }

        return transactionRepository.save(transaction);
    }

    // Get All
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Get by Account ID
    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccount_Id(accountId);
    }

    // DTO mapping
    public TransactionDTO mapToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType()
        );
    }
}