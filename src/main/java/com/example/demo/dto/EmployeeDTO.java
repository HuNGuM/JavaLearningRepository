package com.example.demo.dto;

import java.util.Objects;

public class EmployeeDTO {
    private long id;
    private String fio;
    private int role_id;
    private String login;
    private String password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(long id, String fio, int role_id, String login, String password) {
        this.id = id;
        this.fio = fio;
        this.role_id = role_id;
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
        if (!(o instanceof EmployeeDTO that)) return false;
        return id == that.id && role_id == that.role_id && Objects.equals(fio, that.fio) && Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, role_id, login, password);
    }
}
