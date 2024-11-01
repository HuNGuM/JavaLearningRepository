package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

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

    public Employee() {
    }

    public Employee(Long id, String fio, int role_id, String login, String password) {
        this.id = id;
        this.fio = fio;
        this.role_id = role_id;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return role_id == employee.role_id && Objects.equals(id, employee.id) && Objects.equals(fio, employee.fio) && Objects.equals(login, employee.login) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, role_id, login, password);
    }
}
