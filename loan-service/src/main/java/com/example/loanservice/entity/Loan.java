package com.example.loanservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "loan")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String type;
    public Integer clientId;
}
