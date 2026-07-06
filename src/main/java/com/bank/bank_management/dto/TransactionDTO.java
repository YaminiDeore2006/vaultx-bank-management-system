package com.bank.bank_management.dto;

import jakarta.validation.constraints.*;

public class TransactionDTO {

    private Long id;

    @NotNull(message = "Account ID is required")
    private Long accountId;   // ✅ ADD THIS

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Transaction type is required")
    private String type;

    // Default constructor
    public TransactionDTO() {
    }

    // Updated constructor
    public TransactionDTO(Long id, Long accountId, Double amount, String type) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {   // ✅ ADD THIS
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}