package com.example.patagoniatest.repository;

import com.example.patagoniatest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByFullName(String fullName);
}
