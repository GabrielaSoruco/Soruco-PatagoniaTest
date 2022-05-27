package com.example.patagoniatest.service;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

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

    public void updateClient(Long id, Client newClient){

        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new IllegalStateException("el cliente con el id solicitado, no existe");
        }
        else {
            if (!client.get().equals(newClient.getFullName())){
                client.get().setFullName(newClient.getFullName());
            } else if (!client.get().equals(newClient.getIncome())) {
                client.get().setIncome(newClient.getIncome());
            }
            clientRepository.save(client.get());
        }
    }
}
