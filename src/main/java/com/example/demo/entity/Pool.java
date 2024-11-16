package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "pools")
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true, length = 100)
    private String location;
    @Positive
    @Column(nullable = false, length = 100)
    private int lanes;
    @Column(nullable = false, length = 100)
    private String schedule;
    // One-to-many relationship with Reservation
    @OneToMany(mappedBy = "pool")
    private List<Reservation> reservations;
}