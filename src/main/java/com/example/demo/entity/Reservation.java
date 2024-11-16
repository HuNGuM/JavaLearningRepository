package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    // Many-to-one relationship with Client
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    // Many-to-one relationship with Employee (instructor)
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id", nullable = false)
    private Employee instructor;

    // Many-to-one relationship with Pool
    @ManyToOne
    @JoinColumn(name = "pool_id", referencedColumnName = "id", nullable = false)
    private Pool pool;

    @Column(name = "date_and_time", nullable = false)
    private String dateAndTime;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

