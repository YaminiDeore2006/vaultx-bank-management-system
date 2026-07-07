package com.bank.bank_management.service;

import com.bank.bank_management.dto.FixedDepositDTO;
import com.bank.bank_management.entity.Customer;
import com.bank.bank_management.entity.FixedDeposit;
import com.bank.bank_management.repository.CustomerRepository;
import com.bank.bank_management.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedDepositService {

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // APPLY FD
    // APPLY FD
    public FixedDeposit applyFD(FixedDepositDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Check if customer already has a pending Fixed Deposit request
        if (fixedDepositRepository.existsByCustomer_IdAndStatus(dto.getCustomerId(), "PENDING")) {
            throw new RuntimeException("Customer already has a pending Fixed Deposit request.");
        }

        FixedDeposit fd = new FixedDeposit();

        fd.setCustomer(customer);
        fd.setAmount(dto.getAmount());
        fd.setInterestRate(dto.getInterestRate());
        fd.setTenure(dto.getTenure());

        // Default Status
        fd.setStatus("PENDING");

        return fixedDepositRepository.save(fd);
    }

    // GET ALL FD
    public List<FixedDeposit> getAllFDs() {
        return fixedDepositRepository.findAll();
    }

    // GET PENDING FD
    public List<FixedDeposit> getPendingFDs() {
        return fixedDepositRepository.findByStatus("PENDING");
    }

    // APPROVE FD
    public FixedDeposit approveFD(Long id) {

        FixedDeposit fd = fixedDepositRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FD not found"));

        fd.setStatus("APPROVED");

        return fixedDepositRepository.save(fd);
    }

    // REJECT FD
    public FixedDeposit rejectFD(Long id) {

        FixedDeposit fd = fixedDepositRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FD not found"));

        fd.setStatus("REJECTED");

        return fixedDepositRepository.save(fd);
    }

    // CLOSE FD
    public FixedDeposit closeFD(Long id) {

        FixedDeposit fd = fixedDepositRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FD not found"));

        fd.setStatus("CLOSED");

        return fixedDepositRepository.save(fd);
    }

    // DTO Mapping
    public FixedDepositDTO mapToDTO(FixedDeposit fd) {

        return new FixedDepositDTO(
                fd.getId(),
                fd.getCustomer().getId(),
                fd.getAmount(),
                fd.getInterestRate(),
                fd.getTenure(),
                fd.getStatus()
        );
    }
}