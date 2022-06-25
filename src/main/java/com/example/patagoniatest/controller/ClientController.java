package com.example.patagoniatest.controller;

import com.example.patagoniatest.entity.Client;
import com.example.patagoniatest.model.Loan;
import com.example.patagoniatest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.findClientById(id);
    }

    @PutMapping("/{id}")
    public void updateClient(@RequestBody Client client, @PathVariable Long id) {
        clientService.updateClient(client, id);
    }

    @GetMapping("/average")
    public OptionalDouble averageIncome(){
        return clientService.getEarningsAverage();
    }

    @GetMapping("/top/incomes")
    public List<Client> getTopIncomes(){
        return clientService.getEarningsList();
    }

    @GetMapping("/incomes/{margen}")
    public List<Client> getIncomesPerVariable(@PathVariable Integer margen){
        return clientService.getEarningsByVar(margen);
    }

    @GetMapping("/incomes/average/{margen}")
    public OptionalDouble getEarningPerVariable(@PathVariable Integer margen){
        return clientService.getEarningAveragePerVar(margen);
    }

    @PostMapping("/saveLoan/{clientId}")
    public Loan saveLoan(@PathVariable("clientId") Long clientId, @RequestBody Loan loan){
        return clientService.saveLoan(clientId, loan);
    }
}
