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
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    // =========================
    // CREATE ACCOUNT (FIXED)
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
    public List<AccountDTO> getAllAccounts() {

        return accountService.getAllAccounts()
                .stream()
                .map(accountService::mapToDTO)
                .toList();
    }

    // =========================
// GET ALL ACCOUNTS WITH PAGINATION & SORTING
// =========================
    @GetMapping("/page")
    public Page<AccountDTO> getAccountsWithPagination(

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        logger.info("Fetching accounts with pagination");

        return accountService.getAccounts(pageable)
                .map(accountService::mapToDTO);
    }

    // =========================
    // GET ACCOUNT BY ID
    // =========================
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    // =========================
    // UPDATE ACCOUNT
    // =========================
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id,
                                 @RequestBody Account account) {

        return accountService.updateAccount(id, account);
    }

    // =========================
    // DELETE ACCOUNT
    // =========================
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return "Account Deleted Successfully";
    }

    // =========================
    // DEPOSIT
    // =========================
    @PutMapping("/{id}/deposit")
    public ApiResponse deposit(@PathVariable Long id,
                               @RequestParam double amount) {

        Account account = accountService.deposit(id, amount);

        return new ApiResponse("Deposit successful", account, 200);
    }

    // =========================
    // WITHDRAW
    // =========================
    @PutMapping("/{id}/withdraw")
    public ApiResponse withdraw(@PathVariable Long id,
                                @RequestParam double amount) {

        Account account = accountService.withdraw(id, amount);

        return new ApiResponse("Withdraw successful", account, 200);
    }

    // =========================
    // TRANSACTIONS
    // =========================
    @GetMapping("/{accountId}/transactions")
    public ApiResponse getTransactions(@PathVariable Long accountId) {

        List<Transaction> transactions = transactionService.getTransactions(accountId);

        return new ApiResponse("Transactions fetched", transactions, 200);
    }

    // =========================
// SEARCH ACCOUNT BY ACCOUNT NUMBER
// =========================
    @GetMapping("/search")
    public ApiResponse searchAccountByAccountNumber(
            @RequestParam String accountNumber) {

        logger.info("Searching account with Account Number: {}", accountNumber);

        Account account = accountService.searchByAccountNumber(accountNumber);

        return new ApiResponse(
                "Account found successfully",
                account,
                200
        );
    }
}