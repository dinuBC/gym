package com.gym.model;

import java.util.Set;

public class Trainer {
    private int id;
    private String name;
    private Set<Qualification> qualificationSet;
    private Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Qualification> getQualificationSet() {
        return qualificationSet;
    }

    public void setQualificationSet(Set<Qualification> qualificationSet) {
        this.qualificationSet = qualificationSet;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
