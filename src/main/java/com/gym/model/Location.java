package com.gym.model;

import java.util.Set;

public class Location {
    private int id;
    private String address;
    private String locationName;
    private String phoneNumber;
    private Set<Trainer> trainerSet;
    private Set<Room> roomSet;

    public Set<Trainer> getTrainerSet() {
        return trainerSet;
    }

    public void setTrainerSet(Set<Trainer> trainerSet) {
        this.trainerSet = trainerSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }
}
