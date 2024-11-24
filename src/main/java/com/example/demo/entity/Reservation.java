package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "client_id", length = 90, nullable = false, unique = true)
    private Long client_id;
    @Column(name = "instructor_id", length = 90, nullable = false, unique = true)
    private Long instructor_id;
    @Column(name = "pool_id", length = 90, nullable = false, unique = true)
    private Long pool_id;
    @Column(name = "date_and_time", length = 90, nullable = false)
    private String date_and_time;
    @Column(name = "duration", nullable = false)
    private int duration;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Reservation() {
    }

    public Reservation(Long id, Long client_id, Long instructor_id, Long pool_id, String date_and_time, int duration, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.client_id = client_id;
        this.instructor_id = instructor_id;
        this.pool_id = pool_id;
        this.date_and_time = date_and_time;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(Long instructor_id) {
        this.instructor_id = instructor_id;
    }

    public Long getPool_id() {
        return pool_id;
    }

    public void setPool_id(Long pool_id) {
        this.pool_id = pool_id;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return duration == that.duration && Objects.equals(id, that.id) && Objects.equals(client_id, that.client_id) && Objects.equals(instructor_id, that.instructor_id) && Objects.equals(pool_id, that.pool_id) && Objects.equals(date_and_time, that.date_and_time) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client_id, instructor_id, pool_id, date_and_time, duration, createdAt, updatedAt);
    }

}