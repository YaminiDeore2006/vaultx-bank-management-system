package com.bank.bank_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/accounts-page")
    public String accounts() {
        return "accounts";
    }

    @GetMapping("/transactions-page")
    public String transactionsPage() {
        return "transactions";
    }
}