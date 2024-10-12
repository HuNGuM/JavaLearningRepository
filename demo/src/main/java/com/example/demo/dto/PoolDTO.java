package com.example.demo.dto;

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
}
