package com.gym.model;

import java.time.LocalDateTime;
import java.util.Set;

public class TimeSlot {
    private int id;
    private Trainer trainer;
    private LocalDateTime start;
    private int maxSubscribers = 3;
    private Set<Client> clients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public int getMaxSubscribers() {
        return maxSubscribers;
    }

    public void setMaxSubscribers(int maxSubscribers) {
        this.maxSubscribers = maxSubscribers;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public String getClientList() {
        String output = "";
        for (Client client : clients) {
            output += client.getId() + ",";
        }
        output = output.substring(0, output.length() - 1);
        return output;
    }
}
