package com.soft_con.service;

import com.soft_con.domain.Computer;
import com.soft_con.domain.Customer;
import com.soft_con.repository.ComputerRepository;
import com.soft_con.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ComputerRepository computerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, ComputerRepository computerRepository) {
        this.customerRepository = customerRepository;
        this.computerRepository = computerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllByNameLike(String name) {
        return null;
    }


    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.getComputers().removeAll(customer.getComputers());
        customerRepository.delete(customer);
    }

    @Override
    public Customer addComputer(Long customerId, Long computerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Computer computer = computerRepository.findById(computerId).orElse(null);
        if(customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        if(computer == null) {
            throw new IllegalArgumentException("Computer does not exist");
        }
        customer.addComputer(computer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer removeComputer(Long customerId, Long computerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Computer computer = computerRepository.findById(computerId).orElse(null);
        if(customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        if(computer == null) {
            throw new IllegalArgumentException("Computer does not exist");
        }
        customer.removeComputer(computer);
        return customerRepository.save(customer);
    }
}
