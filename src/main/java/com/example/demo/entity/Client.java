package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fio", length = 90, nullable = false)
    private Long fio;
    @Column(name = "login", length = 90, nullable = false, unique = true)
    private Long login;
    @Column(name = "password", length = 90, nullable = false)
    private Long password;
    @Column(name = "foreign_id", nullable = false)
    private UUID foreignId;  // Foreign key as UUID
}
