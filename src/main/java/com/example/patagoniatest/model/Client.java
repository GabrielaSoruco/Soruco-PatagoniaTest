package com.example.patagoniatest.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    private Long id;
    private String fullName;
    private Integer income;
}
