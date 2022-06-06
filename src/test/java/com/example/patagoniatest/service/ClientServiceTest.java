package com.example.patagoniatest.service;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @BeforeEach
    void setUp(){
        client = new Client(10L, "Lionel Messi", 4000);
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
    void getClients() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1L, "Bruno Mars", 3500));
        clientList.add(new Client(2L, "The Weeknd", 3700));
        when(repository.findAll()).thenReturn(clientList);
        assertNotNull(service.getClients());
        assertEquals( "Bruno Mars", clientList.get(0).getFullName());
    }

    @Test
    void getClient() {
        when(repository.findById(10L)).thenReturn(Optional.of(client));
        assertNotNull(service.findClientById(10L));
    }
}