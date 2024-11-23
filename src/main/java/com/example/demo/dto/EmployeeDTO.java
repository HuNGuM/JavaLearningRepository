package com.example.demo.dto;

import com.example.demo.entity.Role;

import java.util.Objects;

public class EmployeeDTO {
    private long id;
    private String fio;
    private Role role;
    private String login;
    private String password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(long id, String fio, Role role, String login, String password) {
        this.id = id;
        this.fio = fio;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        if (!(o instanceof EmployeeDTO that)) return false;
        return id == that.id && Objects.equals(fio, that.fio) && Objects.equals(role, that.role) && Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, role, login, password);
    }
}
