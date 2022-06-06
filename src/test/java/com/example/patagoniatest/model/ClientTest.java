package com.example.patagoniatest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "Silvestre Soruco", 1000);
    }

    @Test
    void getId() {
        assertEquals(1L, client.getId());
    }

    @Test
    void getFullName() {
        assertEquals("Silvestre Soruco", client.getFullName());
    }

    @Test
    void getIncome() {
        assertEquals(1000, client.getIncome());
    }
}
