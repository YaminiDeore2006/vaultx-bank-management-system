package com.bank.bank_management.controller;

import com.bank.bank_management.dto.FixedDepositDTO;
import com.bank.bank_management.entity.FixedDeposit;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.FixedDepositService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixed-deposits")
public class FixedDepositController {

    @Autowired
    private FixedDepositService fixedDepositService;

    // =========================
    // APPLY FD
    // =========================
    @PostMapping
    public ApiResponse applyFD(@Valid @RequestBody FixedDepositDTO dto) {

        FixedDeposit fd = fixedDepositService.applyFD(dto);

        return new ApiResponse(
                "Fixed Deposit applied successfully",
                fd,
                200
        );
    }

    // =========================
    // GET ALL FD
    // =========================
    @GetMapping
    public ApiResponse getAllFDs() {

        List<FixedDepositDTO> fds = fixedDepositService.getAllFDs()
                .stream()
                .map(fixedDepositService::mapToDTO)
                .toList();

        return new ApiResponse(
                "All Fixed Deposits fetched successfully",
                fds,
                200
        );
    }

    // =========================
    // GET PENDING FD
    // =========================
    @GetMapping("/status/PENDING")
    public ApiResponse getPendingFDs() {

        List<FixedDepositDTO> fds = fixedDepositService.getPendingFDs()
                .stream()
                .map(fixedDepositService::mapToDTO)
                .toList();

        return new ApiResponse(
                "Pending Fixed Deposits fetched successfully",
                fds,
                200
        );
    }

    // =========================
    // APPROVE FD
    // =========================
    @PutMapping("/{id}/approve")
    public ApiResponse approveFD(@PathVariable Long id) {

        FixedDeposit fd = fixedDepositService.approveFD(id);

        return new ApiResponse(
                "Fixed Deposit approved successfully",
                fd,
                200
        );
    }

    // =========================
    // REJECT FD
    // =========================
    @PutMapping("/{id}/reject")
    public ApiResponse rejectFD(@PathVariable Long id) {

        FixedDeposit fd = fixedDepositService.rejectFD(id);

        return new ApiResponse(
                "Fixed Deposit rejected successfully",
                fd,
                200
        );
    }

    // =========================
    // CLOSE FD
    // =========================
    @PutMapping("/{id}/close")
    public ApiResponse closeFD(@PathVariable Long id) {

        FixedDeposit fd = fixedDepositService.closeFD(id);

        return new ApiResponse(
                "Fixed Deposit closed successfully",
                fd,
                200
        );
    }
}