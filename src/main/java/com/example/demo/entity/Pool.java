package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
@Table(name = "pools")
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    // Связь с сотрудником (например, тренером)
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)  // внешний ключ на таблицу employees
    private Employee employee;  // Поле для связи с Employee
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pool pool)) return false;
        return lanes == pool.lanes && Objects.equals(id, pool.id) && Objects.equals(name, pool.name) && Objects.equals(location, pool.location) && Objects.equals(schedule, pool.schedule) && Objects.equals(employee, pool.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, lanes, schedule, employee);
    }

    // Конструкторы, геттеры, сеттеры
    public Pool() {
    }

    public Pool(Long id, String name, String location, int lanes, String schedule, Employee employee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.lanes = lanes;
        this.schedule = schedule;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLanes() {
        return lanes;
    }

    public void setLanes(int lanes) {
        this.lanes = lanes;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}