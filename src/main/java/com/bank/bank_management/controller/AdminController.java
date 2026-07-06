package com.bank.bank_management.controller;

import com.bank.bank_management.dto.DashboardDTO;
import com.bank.bank_management.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        DashboardDTO dashboard = dashboardService.getDashboardData();

        model.addAttribute("dashboard", dashboard);

        return "admin-dashboard";
    }
}