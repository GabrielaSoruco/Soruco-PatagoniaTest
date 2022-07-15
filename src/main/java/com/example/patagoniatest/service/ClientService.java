package com.example.patagoniatest.service;

import com.example.patagoniatest.entity.Client;
//import com.example.patagoniatest.feignclients.LoanFeignClient;
//import com.example.patagoniatest.model.Loan;
import com.example.patagoniatest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    //private final LoanFeignClient loanFeignClient;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client findClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(()-> new IllegalArgumentException("No hay registro con el id: " + id));
    }

    @Transactional
    public void updateClient(Client newClient, Long id){
        Client  client = clientRepository.findById(id).orElseThrow(()->new IllegalStateException("El cliente con el id solicitado, no existe"));
        if (!client.getFullName().equals(newClient.getFullName())){
            client.setFullName(newClient.getFullName());
        }
        if (!client.getIncome().equals(newClient.getIncome())) {
            client.setIncome(newClient.getIncome());
        }
        clientRepository.save(client);
    }

    public OptionalDouble getEarningsAverage() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .mapToDouble(Client::getIncome)
                .average();
    }

    public List<Client> getEarningsList(){
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .filter(client -> client.getIncome()>10000)
                .collect(Collectors.toList());
    }

    public List<Client> getEarningsByVar(Integer margen) {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().filter(client -> client.getIncome()>margen)
                .collect(Collectors.toList());
    }

    public OptionalDouble getEarningAveragePerVar(Integer margen){
        List<Client> clients = clientRepository.findAll();
        return clients.stream().mapToDouble(Client::getIncome)
                .filter(income -> income>margen)
                .average();

    }

//    public Loan saveLoan(Long clientId, Loan loan){
//        Client client = this.findClientById(clientId);
//        loan.setClientId(client.getId());
//        return loanFeignClient.saveLoan(loan);
//    }
//
//    public Map<String, Object> getLoansByClientId(Long clientId){
//        Map<String, Object> loansByClient = new HashMap<>();
//        Client client = this.findClientById(clientId);
//        List<Loan> loans = loanFeignClient.getLoansByClientId(clientId);
//        loansByClient.put("Client", client);
//        loansByClient.put("Loans", loans);
//        return loansByClient;
//    }
}
