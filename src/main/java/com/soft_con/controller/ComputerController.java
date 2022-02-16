package com.soft_con.controller;

import com.soft_con.domain.Computer;
import com.soft_con.service.ComputerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/computers/{id}")
    public Computer getComputer(@PathVariable Long id) {
        return computerService.findById(id).orElseThrow();
    }

    @GetMapping("/computers")
    public List<Computer> getComputers() {
        return computerService.findAll();
    }

    @PostMapping("/computers")
    public Computer createComputer(@RequestBody Computer computer) {
        if(computer.getId() != null) {
            throw new IllegalArgumentException("A new computer cannot already have an ID");
        }
        return computerService.save(computer);
    }

    @PutMapping("/computers")
    public Computer updateComputer(@RequestBody Computer computer) {
        if(computer.getId() == null) {
            throw new IllegalArgumentException("Invalid id");
        }
        if(computerService.findById(computer.getId()).isEmpty()) {
            throw new IllegalArgumentException("Computer does not exist");
        }
        return computerService.save(computer);
    }

    @DeleteMapping("/computers/{id}")
    public void deleteComputer(@PathVariable Long id) {
        computerService.deleteById(id);
    }

}
