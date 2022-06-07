package com.example.patagoniatest.service;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    private Client client;
    private List<Client> clientList;

    @BeforeEach
    void setUp(){
        clientList = new ArrayList<>();
        client = new Client(10L, "Lionel Messi", 4000);
    }

    @Test
    void getClients() {
        clientList.add(client);
        when(repository.findAll()).thenReturn(clientList);
        assertNotNull(service.getClients());
        assertEquals( "Lionel Messi", clientList.get(0).getFullName());
    }

    @Test
    void addClient() {
        when(repository.save(client)).thenReturn(client);
        Client savedClient = service.addClient(client);
        assertEquals(client.getFullName(), savedClient.getFullName());
    }

    @Test
    void deleteClient() {
        doNothing().when(repository).deleteById(client.getId());
        service.deleteClient(client.getId());
        verify(repository).deleteById(client.getId());
        //verify(repository, times(1)).deleteById(client.getId());
    }

    @Test
    void updateClient() {
        when(repository.findById(10L)).thenReturn(Optional.of(client));
        Client updatedClient = new Client(10L, "Stephen Curry", 3800);
        service.updateClient(updatedClient, updatedClient.getId());
        assertEquals(updatedClient.getFullName(), client.getFullName());
        assertEquals(updatedClient.getIncome(), client.getIncome());
    }

    @Test
    void getClient() {
        when(repository.findById(10L)).thenReturn(Optional.of(client));
        assertNotNull(service.findClientById(10L));
    }

    @Test
    void getEarningsAverage() {
        clientList.add(new Client(1L, "Bruno Mars", 4000));
        clientList.add(client);
        when(repository.findAll()).thenReturn(clientList);
        assertEquals(OptionalDouble.of(4000.0), service.getEarningsAverage());
    }

    @Test
    void getEarningsList() {
        clientList.add(new Client(1L, "Bruno Mars", 14000));
        clientList.add(client);
        when(repository.findAll()).thenReturn(clientList);
        assertEquals(List.of(new Client(1L, "Bruno Mars", 14000)), service.getEarningsList());
    }

    @Test
    void getEarningsByVar() {
        clientList.add(new Client(1L, "Bruno Mars", 14000));
        clientList.add(client);
        when(repository.findAll()).thenReturn(clientList);
        assertEquals(List.of(new Client(1L, "Bruno Mars", 14000)), service.getEarningsByVar(10000));
    }

    @Test
    void getEarningAveragePerVar() {
        clientList.add(new Client(1L, "Bruno Mars", 14000));
        clientList.add(client);
        when(repository.findAll()).thenReturn(clientList);
        assertEquals(OptionalDouble.of(14000), service.getEarningAveragePerVar(10000));
    }
}