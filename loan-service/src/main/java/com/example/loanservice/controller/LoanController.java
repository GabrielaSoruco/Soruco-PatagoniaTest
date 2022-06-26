package com.example.loanservice.controller;

import com.example.loanservice.entity.Loan;
import com.example.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loan saveLoan(@RequestBody Loan loan){
        return loanService.saveLoan(loan);
    }

    @GetMapping
    public List<Loan> getAllLoans(){
        return loanService.getLoans();
    }

    @GetMapping("/{clientId}")
    public List<Loan> getLoansByClientId(@PathVariable Long clientId){
        return loanService.getLoansByClientId(clientId);
    }
}