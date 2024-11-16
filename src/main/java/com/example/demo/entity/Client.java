package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
    @Column(name = "external_id", nullable = false, unique = true)
    private UUID externalId;
    // One-to-many relationship with Reservation
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
}
