package com.example.patagoniatest.repository;

import com.example.patagoniatest.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    ClientRepository repository;

    @Test
    public void saveClient(){
        Client clienteRepo = repository.save(new Client(1l, "Silvestre Soruco", 2000));
        assertNotNull(clienteRepo);
        assertTrue(clienteRepo.getId() > 0);
    }

    @Test
    public void getClientsTest(){
        List<Client> clientes = repository.findAll();
        assertNotNull(clientes);
        assertEquals(3, clientes.size());
    }

    @Test
    public void getClientTest(){
        Optional<Client> client = repository.findById(1l);
        assertTrue(client.isPresent());
        assertEquals("Lady Gaga", client.get().getFullName());
        assertNotNull(client);
    }

    @Test
    public void deleteClientTest(){
        Client client = repository.save(new Client(5l, "Shakira", 3200));
        repository.delete(client);
        Client clientPrueba = repository.findByFullName("Shakira");
        assertNull(clientPrueba);
    }

    @Test
    public void updateClient(){
        Client client1 = repository.findById(3l).get();
        client1.setFullName("Shakira");
        repository.save(client1);
        assertEquals("Shakira", client1.getFullName());
    }
}