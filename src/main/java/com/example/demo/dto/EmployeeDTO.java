package com.example.demo.dto;

import java.util.Objects;

public class EmployeeDTO {
    private long id;
    private String fio;
    private int role_id;

    public EmployeeDTO(long id, String fio, int role_id) {
        this.id = id;
        this.fio = fio;
        this.role_id = role_id;
    }

    public EmployeeDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO that)) return false;
        return id == that.id && role_id == that.role_id && Objects.equals(fio, that.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, role_id);
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
}
