package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fio", length = 90, nullable = false)
    private String fio;
    @Column(name = "login", length = 90, nullable = false, unique = true)
    private String login;
    @Column(name = "password", length = 90, nullable = false)
    private String password;

}