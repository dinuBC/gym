package com.gym.model;

import java.util.Set;

public class Client {
    private int id;
    private String name;
    private String phonenumber;
    private Set<TimeSlot> subscribedSlots;

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Set<TimeSlot> getSubscribedSlots() {
        return subscribedSlots;
    }

    public void setSubscribedSlots(Set<TimeSlot> subscribedSlots) {
        this.subscribedSlots = subscribedSlots;
    }
}
