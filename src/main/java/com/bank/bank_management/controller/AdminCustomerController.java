package com.bank.bank_management.controller;

import com.bank.bank_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String customers(Model model) {

        model.addAttribute(
                "customers",
                customerService.getAllCustomers()
        );

        return "customers";
    }

}