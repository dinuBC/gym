package com.gym.repository;

import com.gym.model.Client;
import com.gym.model.TimeSlot;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClientRepository {
    private static final String[] HEADERS = {"Identifier", "Name", "Phone", "Subscribed_Slots"};
    private static final String clientDBLocation = "src/main/resources/clientDB.csv";

    public static void generateClientDB(Set<Client> clientSet) throws IOException {
        FileWriter out = new FileWriter(clientDBLocation);
        CSVPrinter printer = CSVFormat.DEFAULT.withHeader(HEADERS).print(out);
        for (Client client : clientSet) {
            printer.printRecord(client.getId(), client.getName(), client.getPhonenumber(), client.getSubscribedSlots());
        }
        printer.flush();
        printer.close();
        out.close();
    }
    public static Set<Client> readClientDB() throws IOException {
        Set<Client> dbClients = new LinkedHashSet<>();
        Reader in = new FileReader(clientDBLocation);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String id = record.get("Identifier");
            String name = record.get("Name");
            String phone = record.get("Phone");
            String slotsAsString = record.get("Subscribed_Slots");
            Client client = new Client();
            client.setId(Integer.parseInt(id));
            client.setName(name);
            client.setPhonenumber(phone);
            Set<TimeSlot> timeSlotSet = new LinkedHashSet<>();
            List<String> clientTimeSlotList = Arrays.asList(slotsAsString.split(","));
            for (String slot : clientTimeSlotList) {
                TimeSlot tempTimeSlot = new TimeSlot();
                tempTimeSlot.setId(Integer.parseInt(slot));
                timeSlotSet.add(tempTimeSlot);
            }
            client.setSubscribedSlots(timeSlotSet);
            dbClients.add(client);
        }
        return dbClients;
    }

    public static void updateClientDB(int id, Client updatedClient) throws IOException {
        Set<Client> tempClients = ClientRepository.readClientDB();
        for (Client client : tempClients) {
            if (client.getId() == id) {
                client.setSubscribedSlots(updatedClient.getSubscribedSlots());
                client.setPhonenumber(updatedClient.getPhonenumber());
                client.setName(updatedClient.getName());
            }
        }
        ClientRepository.generateClientDB(tempClients);
    }

    public static void deleteEntry(int id) throws IOException {
        Set<Client> tempClients = ClientRepository.readClientDB();
        Set<Client> newClientDB = new LinkedHashSet<>();
        for (Client client : tempClients) {
            if (client.getId() != id) {
                newClientDB.add(client);
            }
        }
        ClientRepository.generateClientDB(newClientDB);
    }
}
