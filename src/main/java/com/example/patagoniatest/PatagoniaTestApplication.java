package com.example.patagoniatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PatagoniaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatagoniaTestApplication.class, args);
    }

}
