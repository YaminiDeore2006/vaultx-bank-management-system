package com.bank.bank_management.service;

import com.bank.bank_management.dto.CustomerDTO;
import com.bank.bank_management.entity.Customer;
import com.bank.bank_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // =========================
    // CREATE CUSTOMER
    // =========================
    public Customer createCustomer(CustomerDTO dto) {

        Customer customer = new Customer();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        return customerRepository.save(customer);
    }

    // =========================
    // GET ALL CUSTOMERS
    // =========================
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // =========================
    // GET CUSTOMER BY ID
    // =========================
    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }

    // =========================
    // UPDATE CUSTOMER
    // =========================
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existing = getCustomerById(id);

        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setAddress(customer.getAddress());

        return customerRepository.save(existing);
    }

    // =========================
    // DELETE CUSTOMER
    // =========================
    public void deleteCustomer(Long id) {

        customerRepository.deleteById(id);
    }

    // =========================
    // SEARCH CUSTOMER BY EMAIL
    // =========================
    public Customer searchByEmail(String email) {

        return customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }

    // =========================
    // DTO MAPPING
    // =========================
    public CustomerDTO mapToDTO(Customer customer) {

        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}