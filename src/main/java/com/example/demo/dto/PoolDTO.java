package com.example.demo.dto;

import java.util.Objects;

public class PoolDTO {
    private Long id;
    private String name;
    private String location;
    private int lanes;
    private String schedule;

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

    public PoolDTO() {
    }

    public PoolDTO(Long id, String name, String location, int lanes, String schedule) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.lanes = lanes;
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoolDTO poolDTO)) return false;
        return lanes == poolDTO.lanes && Objects.equals(id, poolDTO.id) && Objects.equals(name, poolDTO.name) && Objects.equals(location, poolDTO.location) && Objects.equals(schedule, poolDTO.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, lanes, schedule);
    }
}
