package com.mobinyousefi.traffic.model;

import java.time.LocalDateTime;

public class TrafficSignal {

    public enum State {
        RED,
        YELLOW,
        GREEN,
        OFFLINE
    }

    private long id;
    private String intersectionName;
    private String direction; // e.g. N, S, E, W
    private State state;
    private LocalDateTime lastUpdated;

    public TrafficSignal() {
    }

    public TrafficSignal(long id, String intersectionName, String direction, State state, LocalDateTime lastUpdated) {
        this.id = id;
        this.intersectionName = intersectionName;
        this.direction = direction;
        this.state = state;
        this.lastUpdated = lastUpdated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntersectionName() {
        return intersectionName;
    }

    public void setIntersectionName(String intersectionName) {
        this.intersectionName = intersectionName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
