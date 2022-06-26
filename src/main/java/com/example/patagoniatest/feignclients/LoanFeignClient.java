package com.example.patagoniatest.feignclients;

import com.example.patagoniatest.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "loan-service")
@RequestMapping("/loan")
public interface LoanFeignClient {

    @PostMapping
    Loan saveLoan(@RequestBody Loan loan);

    @GetMapping("/{clientId}")
    List<Loan> getLoansByClientId(@PathVariable Long clientId);
}
