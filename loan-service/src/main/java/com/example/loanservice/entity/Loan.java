package com.example.loanservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "loan")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String type;
    private Long clientId;
}
