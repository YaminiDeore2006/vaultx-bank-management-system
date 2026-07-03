package com.bank.bank_management.dto;

import jakarta.validation.constraints.*;

public class TransactionDTO {

    private Long id;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Transaction type is required")
    private String type;

    // Default constructor
    public TransactionDTO() {
    }

    // Parameterized constructor
    public TransactionDTO(Long id, Double amount, String type) {
        this.id = id;
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