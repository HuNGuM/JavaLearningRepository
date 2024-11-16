package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "FIO", length = 90, nullable = false, unique = true)
    private String fio;
    @Column(name = "role_id", length = 90, nullable = false)
    private int role_id;
    @Column(name = "login", length = 90, nullable = false, unique = true)
    private String login;
    @Column(name = "password", length = 90, nullable = false)
    private String password;
    // Many-to-one relationship with Role
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    // One-to-many relationship with Reservation
    @OneToMany(mappedBy = "instructor")
    private List<Reservation> reservations;
}