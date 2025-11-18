package com.mobinyousefi.traffic.model;

public class Route {

    public enum Status {
        OPEN,
        CLOSED,
        CONGESTED
    }

    private long id;
    private String name;
    private String fromIntersection;
    private String toIntersection;
    private Status status;

    public Route() {
    }

    public Route(long id, String name, String fromIntersection, String toIntersection, Status status) {
        this.id = id;
        this.name = name;
        this.fromIntersection = fromIntersection;
        this.toIntersection = toIntersection;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromIntersection() {
        return fromIntersection;
    }

    public void setFromIntersection(String fromIntersection) {
        this.fromIntersection = fromIntersection;
    }

    public String getToIntersection() {
        return toIntersection;
    }

    public void setToIntersection(String toIntersection) {
        this.toIntersection = toIntersection;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
