package com.example.touristguide.domain;

public class Destination {
    private int destination_id;
    private String name;
    private String description;

    public Destination(int destination_id, String name, String description) {
        this.destination_id = destination_id;
        this.name = name;
        this.description = description;
    }

    public Destination() {
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
