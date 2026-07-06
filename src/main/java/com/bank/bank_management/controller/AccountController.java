package com.bank.bank_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bank.bank_management.dto.AccountDTO;
import com.bank.bank_management.entity.Account;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.AccountService;
import com.bank.bank_management.service.TransactionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger logger =
            LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    // =========================
    // CREATE ACCOUNT
    // =========================
    @PostMapping
    public ResponseEntity<ApiResponse> createAccount(@Valid @RequestBody AccountDTO dto) {

        Account account = accountService.createAccount(dto);

        return ResponseEntity.ok(
                new ApiResponse("Account created successfully", account, 200)
        );
    }

    // =========================
    // GET ALL ACCOUNTS
    // =========================
    @GetMapping
    public ResponseEntity<ApiResponse> getAllAccounts() {

        List<AccountDTO> accounts = accountService.getAllAccounts()
                .stream()
                .map(accountService::mapToDTO)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse("Accounts fetched successfully", accounts, 200)
        );
    }

    // =========================
    // PAGINATION + SORTING
    // =========================
    @GetMapping("/page")
    public ResponseEntity<ApiResponse> getAccountsWithPagination(

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        logger.info("Fetching accounts with pagination");

        Page<AccountDTO> page = accountService.getAccounts(pageable)
                .map(accountService::mapToDTO);

        return ResponseEntity.ok(
                new ApiResponse("Paged accounts fetched", page, 200)
        );
    }

    // =========================
    // GET ACCOUNT BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAccountById(@PathVariable Long id) {

        Account account = accountService.getAccountById(id);

        return ResponseEntity.ok(
                new ApiResponse("Account fetched", account, 200)
        );
    }

    // =========================
    // UPDATE ACCOUNT
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAccount(@PathVariable Long id,
                                                     @RequestBody Account account) {

        Account updated = accountService.updateAccount(id, account);

        return ResponseEntity.ok(
                new ApiResponse("Account updated successfully", updated, 200)
        );
    }

    // =========================
    // DELETE ACCOUNT
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return ResponseEntity.ok(
                new ApiResponse("Account deleted successfully", null, 200)
        );
    }

    // =========================
    // DEPOSIT
    // =========================
    @PutMapping("/{id}/deposit")
    public ResponseEntity<ApiResponse> deposit(@PathVariable Long id,
                                               @RequestParam double amount) {

        Account account = accountService.deposit(id, amount);

        return ResponseEntity.ok(
                new ApiResponse("Deposit successful", account, 200)
        );
    }

    // =========================
    // WITHDRAW
    // =========================
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<ApiResponse> withdraw(@PathVariable Long id,
                                                @RequestParam double amount) {

        Account account = accountService.withdraw(id, amount);

        return ResponseEntity.ok(
                new ApiResponse("Withdraw successful", account, 200)
        );
    }

    // =========================
    // TRANSACTIONS
    // =========================
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<ApiResponse> getTransactions(@PathVariable Long accountId) {

        List<Transaction> transactions =
                transactionService.getTransactions(accountId);

        return ResponseEntity.ok(
                new ApiResponse("Transactions fetched", transactions, 200)
        );
    }

    // =========================
    // SEARCH ACCOUNT
    // =========================
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchAccountByAccountNumber(
            @RequestParam String accountNumber) {

        logger.info("Searching account: {}", accountNumber);

        Account account =
                accountService.searchByAccountNumber(accountNumber);

        return ResponseEntity.ok(
                new ApiResponse("Account found successfully", account, 200)
        );
    }
}