package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)  // Указываем внешний ключ
    private Role role;  // Связь с сущностью Role
    @Column(name = "login", length = 90, nullable = false, unique = true)
    private String login;
    @Column(name = "password", length = 90, nullable = false)
    private String password;

    public Employee() {
    }

    public Employee(Long id, String fio, Role role, String login, String password) {
        this.id = id;
        this.fio = fio;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        return Objects.equals(id, employee.id) && Objects.equals(fio, employee.fio) && Objects.equals(role, employee.role) && Objects.equals(login, employee.login) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, role, login, password);
    }
}