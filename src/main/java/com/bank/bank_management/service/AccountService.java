package com.bank.bank_management.service;

import com.bank.bank_management.entity.Account;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.repository.AccountRepository;
import com.bank.bank_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.bank_management.exception.AccountNotFoundException;
import com.bank.bank_management.exception.InsufficientBalanceException;
import com.bank.bank_management.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private String generateAccountNumber() {

        Random random = new Random();

        String accountNumber;

        do {
            accountNumber = "VX" + (1000000000L + Math.abs(random.nextLong() % 9000000000L));
        }
        while (accountRepository.findByAccountNumber(accountNumber).isPresent());

        return accountNumber;
    }

    // ================= CREATE ACCOUNT =================
    public Account saveAccount(Account account) {

        logger.info("Saving new account: {}", account.getAccountNumber());

        return accountRepository.save(account);
    }
    // ================= GET ALL ACCOUNTS =================
    public List<Account> getAllAccounts() {

        logger.info("Fetching all accounts");

        return accountRepository.findAll();
    }

    public Page<Account> getAccounts(Pageable pageable) {

        logger.info("Fetching accounts with pagination");

        return accountRepository.findAll(pageable);
    }

    // ================= GET ACCOUNT BY ID =================
    public Account getAccountById(Long id) {

        logger.info("Fetching account with ID: {}", id);

        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    // ================= UPDATE ACCOUNT =================
    public Account updateAccount(Long id, Account updatedAccount) {

        logger.info("Updating account with ID: {}", id);

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        account.setAccountNumber(updatedAccount.getAccountNumber());
        account.setAccountType(updatedAccount.getAccountType());
        account.setBalance(updatedAccount.getBalance());
        account.setStatus(updatedAccount.getStatus());

        Account updated = accountRepository.save(account);

        logger.info("Account updated successfully with ID: {}", id);

        return updated;
    }

    // ================= DELETE ACCOUNT =================
    public void deleteAccount(Long id) {

        logger.info("Deleting account with ID: {}", id);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        accountRepository.delete(account);
    }

    // ================= DEPOSIT =================
    public Account deposit(Long id, Double amount) {

        logger.info("Deposit request. Account ID: {}, Amount: {}", id, amount);

        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        // 🔥 NULL SAFE FIX
        double balance = account.getBalance() == null ? 0.0 : account.getBalance();

        account.setBalance(balance + amount);
        Account updatedAccount = accountRepository.save(account);

        // Transaction save
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transaction.setDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        logger.info("Deposit successful for Account ID: {}", id);
        return updatedAccount;
    }

    // ================= WITHDRAW =================
    public Account withdraw(Long id, Double amount) {

        logger.info("Withdraw request. Account ID: {}, Amount: {}", id, amount);

        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        // 🔥 NULL SAFE FIX
        double balance = account.getBalance() == null ? 0.0 : account.getBalance();

        double minimumBalance = 1000.0;

        if ((balance - amount) < minimumBalance) {
            throw new InsufficientBalanceException(
                    "Minimum balance of ₹1000 must be maintained."
            );
        }

        account.setBalance(balance - amount);
        Account updatedAccount = accountRepository.save(account);

        // Transaction save
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("WITHDRAW");
        transaction.setDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        logger.info("Withdrawal successful for Account ID: {}", id);
        return updatedAccount;
    }

    // ================= TRANSACTIONS =================
    public List<Transaction> getTransactions(Long accountId) {

        logger.info("Fetching transactions for Account ID: {}", accountId);

        return transactionRepository.findByAccount_Id(accountId);
    }

    // ================= BALANCE =================
    public Double getBalance(Long id) {
        logger.info("Fetching balance for Account ID: {}", id);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

        return account.getBalance();
    }

    public AccountDTO mapToDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getStatus()
        );
    }

    public Account createAccount(AccountDTO dto) {
        logger.info("Creating account with Account Number: {}", dto.getAccountNumber());
        Account account = new Account();

        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setStatus(dto.getStatus());
        Account savedAccount = accountRepository.save(account);

        logger.info("Account created successfully with ID: {}", savedAccount.getId());

        return savedAccount;
    }

    public Account searchByAccountNumber(String accountNumber) {

        logger.info("Searching account with Account Number: {}", accountNumber);

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account not found with Account Number: " + accountNumber));
    }
}