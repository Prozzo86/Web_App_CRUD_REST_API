package com.soft_con.service;

import com.soft_con.domain.Computer;

import java.util.List;
import java.util.Optional;

public interface ComputerService {

    Computer save(Computer computer);

    Optional<Computer> findById(Long id);

    List<Computer> findAll();

    List<Computer> findAllByCustomerId(Long customerId);

    void deleteById(Long id);

}
