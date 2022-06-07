package com.example.patagoniatest.controller;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService service;

    Client client;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        client = new Client(12L, "Manuel Ginobili", 3000);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getClients() throws Exception {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(10L, "Lionel Messi", 4000));
        clientList.add(client);
        when(service.getClients()).thenReturn(clientList);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$[0].fullName").value("Lionel Messi"));
        verify(service).getClients();
    }

    @Test
    void getClient() throws Exception {
        when(service.findClientById(client.getId())).thenReturn(client);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients/12").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.fullName").value("Manuel Ginobili"))
                .andExpect(jsonPath("$.income").value(3000));
        verify(service).findClientById(client.getId());
    }

    @Test
    void addClient() throws Exception {
        when(service.addClient(client)).thenReturn(client);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/clients/addClient").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.fullName").value("Manuel Ginobili"))
                .andExpect(jsonPath("$.income").value(3000));
    }

    @Test
    void deleteClient() throws Exception {
        doNothing().when(service).deleteClient(client.getId());
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/clients/deleteClient/{id}",client.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        verify(service).deleteClient(12L);
    }

    @Test
    void updateClient() throws Exception {
        doNothing().when(service).updateClient(client, client.getId());
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/clients/{id}", client.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).updateClient(client, client.getId());
    }

    @Test
    void averageIncome() throws Exception {
        when(service.getEarningsAverage()).thenReturn(OptionalDouble.of(1000));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients/average").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string("1000.0"))
                .andDo(print());
    }

    @Test
    void getTopIncomes() throws Exception {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1L, "Brad Pitt", 12000));
        clientList.add(client);
        when(service.getEarningsList()).thenReturn(clientList);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients/top/incomes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Brad Pitt"))
                .andDo(print());
    }

    @Test
    void getIncomesPerVariable() throws Exception {
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        when(service.getEarningsByVar(2000)).thenReturn(clientList);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients/incomes/{margen}",2000).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Manuel Ginobili"))
                .andDo(print());
        verify(service).getEarningsByVar(2000);
    }

    @Test
    void getEarningPerVariable() throws Exception {
        when(service.getEarningAveragePerVar(2000)).thenReturn(OptionalDouble.of(3000));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients/incomes/average/{margen}", 2000).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("3000.0"))
                .andDo(print());
    }
}
