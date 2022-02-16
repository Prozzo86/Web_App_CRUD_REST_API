package com.soft_con.controller;

import com.soft_con.domain.Computer;
import com.soft_con.domain.Customer;
import com.soft_con.service.ComputerService;
import com.soft_con.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    private final ComputerService computerService;

    public CustomerController(CustomerService customerService, ComputerService computerService) {
        this.customerService = customerService;
        this.computerService = computerService;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.findById(id).orElseThrow();
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(@RequestParam(required = false) String name) {
        if(name != null) {
            return customerService.findAllByNameLike(name);
        } else {
            return customerService.findAll();
        }
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        if(customer.getId() != null) {
            throw new IllegalArgumentException("A new computer cannot already have an ID");
        }
        return customerService.save(customer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        if(customer.getId() == null) {
            throw new IllegalArgumentException("Invalid id");
        }
        if(customerService.findById(customer.getId()).isEmpty()) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        return customerService.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
    }

    @GetMapping("/customers/{id}/computer")
    public List<Computer> getCustomerComputers(@PathVariable Long id) {
        return computerService.findAllByCustomerId(id);
    }

    @PutMapping("/customers/{id}/computer/{computerId}")
    public Customer addComputer(@PathVariable Long id, @PathVariable Long computerId) {
        return customerService.addComputer(id, computerId);
    }

    @DeleteMapping("/customers/{id}/computer/{computerId}")
    public Customer removeComputer(@PathVariable Long id, @PathVariable Long computerId) {
        return customerService.removeComputer(id, computerId);
    }

}
