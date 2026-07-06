package com.bank.bank_management.controller;

import com.bank.bank_management.dto.CustomerDTO;
import com.bank.bank_management.entity.Customer;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // =========================
    // CREATE CUSTOMER
    // =========================
    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(
            @Valid @RequestBody CustomerDTO dto) {

        Customer customer = customerService.createCustomer(dto);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Customer created successfully",
                        customer,
                        200
                )
        );
    }

    // =========================
    // GET ALL CUSTOMERS
    // =========================
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomers() {

        List<CustomerDTO> customers = customerService.getAllCustomers()
                .stream()
                .map(customerService::mapToDTO)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse(
                        "Customers fetched successfully",
                        customers,
                        200
                )
        );
    }

    // =========================
    // GET CUSTOMER BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(
            @PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Customer fetched successfully",
                        customer,
                        200
                )
        );
    }

    // =========================
    // UPDATE CUSTOMER
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Customer updatedCustomer =
                customerService.updateCustomer(id, customer);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Customer updated successfully",
                        updatedCustomer,
                        200
                )
        );
    }

    // =========================
    // DELETE CUSTOMER
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Customer deleted successfully",
                        null,
                        200
                )
        );
    }

    // =========================
    // SEARCH CUSTOMER BY EMAIL
    // =========================
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchCustomerByEmail(
            @RequestParam String email) {

        Customer customer = customerService.searchByEmail(email);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Customer found successfully",
                        customer,
                        200
                )
        );
    }
}