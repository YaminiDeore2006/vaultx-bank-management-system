package com.bank.bank_management.service;

import com.bank.bank_management.dto.DashboardDTO;
import com.bank.bank_management.entity.Account;
import com.bank.bank_management.repository.AccountRepository;
import com.bank.bank_management.repository.CardRepository;
import com.bank.bank_management.repository.CustomerRepository;
import com.bank.bank_management.repository.FixedDepositRepository;
import com.bank.bank_management.repository.LoanRepository;
import com.bank.bank_management.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    public DashboardDTO getDashboardData() {

        long totalCustomers = customerRepository.count();

        long totalAccounts = accountRepository.count();

        long totalTransactions = transactionRepository.count();

        long totalLoans = loanRepository.count();

        long totalCards = cardRepository.count();

        long totalFixedDeposits = fixedDepositRepository.count();

        long pendingLoans = loanRepository.findByStatus("PENDING").size();

        long pendingCards = cardRepository.findByStatus("PENDING").size();

        long pendingFixedDeposits =
                fixedDepositRepository.findByStatus("PENDING").size();

        List<Account> accounts = accountRepository.findAll();

        double totalBalance = 0;

        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }

        return new DashboardDTO(
                totalCustomers,
                totalAccounts,
                totalTransactions,
                totalBalance,
                totalLoans,
                totalCards,
                totalFixedDeposits,
                pendingLoans,
                pendingCards,
                pendingFixedDeposits
        );
    }
}