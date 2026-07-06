package com.bank.bank_management.controller;

import com.bank.bank_management.dto.TransferDTO;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ApiResponse transferMoney(@Valid @RequestBody TransferDTO dto) {

        return transferService.transferMoney(dto);
    }
}