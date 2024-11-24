package com.example.demo.dto;

import com.example.demo.entity.Employee;

import java.util.Objects;

public class PoolDTO {
    private Long id;
    private String name;
    private String location;
    private int lanes;
    private String schedule;
    private Employee employee;

    public PoolDTO() {
    }

    public PoolDTO(Long id, String name, String location, int lanes, String schedule, Employee employee) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoolDTO poolDTO)) return false;
        return lanes == poolDTO.lanes && Objects.equals(id, poolDTO.id) && Objects.equals(name, poolDTO.name) && Objects.equals(location, poolDTO.location) && Objects.equals(schedule, poolDTO.schedule) && Objects.equals(employee, poolDTO.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, lanes, schedule, employee);
    }
}