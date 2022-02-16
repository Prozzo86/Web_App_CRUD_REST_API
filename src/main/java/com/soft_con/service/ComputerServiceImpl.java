package com.soft_con.service;

import com.soft_con.domain.Computer;
import com.soft_con.domain.Customer;
import com.soft_con.repository.ComputerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComputerServiceImpl implements ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public Computer save(Computer computer) {
        return computerRepository.save(computer);
    }

    @Override
    public Optional<Computer> findById(Long id) {
        return computerRepository.findById(id);
    }

    @Override
    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public List<Computer> findAllByCustomerId(Long customerId) {
        return null;
    }


    @Override
    public void deleteById(Long id) {
        Computer computer = computerRepository.findById(id).get();
        for (Customer customer : computer.getCustomers()) {
            customer.removeComputer(computer);
        }
        computerRepository.delete(computer);
    }

}
