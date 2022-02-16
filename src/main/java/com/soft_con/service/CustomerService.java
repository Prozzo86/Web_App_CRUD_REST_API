package com.soft_con.service;

import com.soft_con.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    List<Customer> findAllByNameLike(String name);

    Optional<Customer> findById(Long id);

    void deleteById(Long id);

    Customer addComputer(Long customerId, Long computerId);

    Customer removeComputer(Long customerId, Long computerId);
}
