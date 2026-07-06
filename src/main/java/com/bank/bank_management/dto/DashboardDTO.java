package com.bank.bank_management.dto;

public class DashboardDTO {

    private long totalCustomers;
    private long totalAccounts;
    private long totalTransactions;
    private Double totalBalance;

    private long totalLoans;
    private long totalCards;
    private long totalFixedDeposits;

    private long pendingLoans;
    private long pendingCards;
    private long pendingFixedDeposits;

    public DashboardDTO() {
    }

    public DashboardDTO(long totalCustomers,
                        long totalAccounts,
                        long totalTransactions,
                        Double totalBalance,
                        long totalLoans,
                        long totalCards,
                        long totalFixedDeposits,
                        long pendingLoans,
                        long pendingCards,
                        long pendingFixedDeposits) {

        this.totalCustomers = totalCustomers;
        this.totalAccounts = totalAccounts;
        this.totalTransactions = totalTransactions;
        this.totalBalance = totalBalance;

        this.totalLoans = totalLoans;
        this.totalCards = totalCards;
        this.totalFixedDeposits = totalFixedDeposits;

        this.pendingLoans = pendingLoans;
        this.pendingCards = pendingCards;
        this.pendingFixedDeposits = pendingFixedDeposits;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(long totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public long getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(long totalLoans) {
        this.totalLoans = totalLoans;
    }

    public long getTotalCards() {
        return totalCards;
    }

    public void setTotalCards(long totalCards) {
        this.totalCards = totalCards;
    }

    public long getTotalFixedDeposits() {
        return totalFixedDeposits;
    }

    public void setTotalFixedDeposits(long totalFixedDeposits) {
        this.totalFixedDeposits = totalFixedDeposits;
    }

    public long getPendingLoans() {
        return pendingLoans;
    }

    public void setPendingLoans(long pendingLoans) {
        this.pendingLoans = pendingLoans;
    }

    public long getPendingCards() {
        return pendingCards;
    }

    public void setPendingCards(long pendingCards) {
        this.pendingCards = pendingCards;
    }

    public long getPendingFixedDeposits() {
        return pendingFixedDeposits;
    }

    public void setPendingFixedDeposits(long pendingFixedDeposits) {
        this.pendingFixedDeposits = pendingFixedDeposits;
    }
}