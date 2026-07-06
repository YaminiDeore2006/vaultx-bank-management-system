package com.bank.bank_management.service;

import com.bank.bank_management.dto.TransferDTO;
import com.bank.bank_management.entity.Account;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.repository.AccountRepository;
import com.bank.bank_management.repository.TransactionRepository;
import com.bank.bank_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ApiResponse transferMoney(TransferDTO dto) {

        Account sender = accountRepository.findById(dto.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender Account Not Found"));

        Account receiver = accountRepository.findById(dto.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver Account Not Found"));

        if (sender.getId().equals(receiver.getId())) {
            throw new RuntimeException("Sender and Receiver cannot be same.");
        }

        if (sender.getBalance() < dto.getAmount()) {
            throw new RuntimeException("Insufficient Balance.");
        }

        // Update Balances
        sender.setBalance(sender.getBalance() - dto.getAmount());
        receiver.setBalance(receiver.getBalance() + dto.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        // Debit Transaction
        Transaction debit = new Transaction();
        debit.setAccount(sender);
        debit.setAmount(dto.getAmount());
        debit.setType("DEBIT");
        debit.setDate(LocalDateTime.now());

        transactionRepository.save(debit);

        // Credit Transaction
        Transaction credit = new Transaction();
        credit.setAccount(receiver);
        credit.setAmount(dto.getAmount());
        credit.setType("CREDIT");
        credit.setDate(LocalDateTime.now());

        transactionRepository.save(credit);

        return new ApiResponse(
                "Money transferred successfully",
                null,
                200
        );
    }
}