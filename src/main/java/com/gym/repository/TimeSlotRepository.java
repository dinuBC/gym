package com.gym.repository;

import com.gym.model.Client;
import com.gym.model.TimeSlot;
import com.gym.model.Trainer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeSlotRepository {
    private static final String[] HEADERS = {"Identifier", "Trainer_ID", "Start_Time", "No_of_Subscribers", "Clients"};
    private static final String timeSlotDBLocation = "src/main/resources/timeSlotDB.csv";

    //added a formatter for date and time to easily write date and time to file and parse back
    private static DateTimeFormatter startFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    public static void generateTimeSlotDB(Set<TimeSlot> timeSlots) throws IOException {
        FileWriter out = new FileWriter(timeSlotDBLocation);
        CSVPrinter printer = CSVFormat.DEFAULT.withHeader(HEADERS).print(out);
        for (TimeSlot slot : timeSlots) {
            printer.printRecord(slot.getId(), slot.getTrainer().getId(), slot.getStart().format(startFormatter), slot.getMaxSubscribers(), slot.getClientList());
        }
        printer.flush();
        printer.close();
        out.close();
    }

    // this method receives as parameter a client ID and returns the number of slots to which he has subscribed
    public static int clientBookedSlotsInt(int clientID) {
        int numberOfSlots = 0;
        try {
            Set<TimeSlot> dbTimeSlots = TimeSlotRepository.readTimeSlotDB();
            for (TimeSlot slot : dbTimeSlots) {
                Set<Client> tempClients = slot.getClients();
                for (Client client : tempClients) {
                    if (client.getId() == clientID) {
                        numberOfSlots++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfSlots;
    }

    public static Set<TimeSlot> readTimeSlotDB() throws IOException {
        Set<TimeSlot> dbTimeSlot = new LinkedHashSet<>();
        Reader in = new FileReader(timeSlotDBLocation);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {

            //get the CSV data as strings
            String id = record.get("Identifier");
            String trainerID = record.get("Trainer_ID");
            String startTime = record.get("Start_Time");
            String subscriberNo = record.get("No_of_Subscribers");
            String clientsAsString = record.get("Clients");

            //create a TimeSlot object and fill it in with the data read in the previous step
            TimeSlot tempSlot = new TimeSlot();
            tempSlot.setId(Integer.parseInt(id));
            Trainer tempTrainer = new Trainer();
            tempTrainer.setId(Integer.parseInt(trainerID));
            tempSlot.setTrainer(tempTrainer);
            //parse back the string containing the date and time to an object using the formatter
            LocalDateTime tempStartTime = LocalDateTime.parse(startTime, startFormatter);
            tempSlot.setStart(tempStartTime);
            tempSlot.setMaxSubscribers(Integer.parseInt(subscriberNo));

            //the database contains a list of client IDs split by commas; split them and put them into a list of IDs,
            //iterate through the list and for each of them create a client, set the ID and add the Client Object to a set
            //add the set to the time slot object
            Set<Client> clientSet = new LinkedHashSet<>();
            List<String> subscribedClientList = Arrays.asList(clientsAsString.split(","));
            for (String clientID : subscribedClientList) {
                Client tempClient = new Client();
                tempClient.setId(Integer.parseInt(clientID));
                clientSet.add(tempClient);
            }
            tempSlot.setClients(clientSet);
            //add the temporary TimeSlot to the set that is going to be returned
            dbTimeSlot.add(tempSlot);
        }
        return dbTimeSlot;
    }

    public static void updateTimeSlotDB(int id, TimeSlot updatedTimeSlot) throws IOException {
        Set<TimeSlot> tempTimeSlots = TimeSlotRepository.readTimeSlotDB();
        for (TimeSlot slot : tempTimeSlots) {
            if (slot.getId() == id) {
                slot.setClients(updatedTimeSlot.getClients());
                slot.setMaxSubscribers(updatedTimeSlot.getMaxSubscribers());
                slot.setStart(updatedTimeSlot.getStart());
                slot.setTrainer(updatedTimeSlot.getTrainer());
            }
        }
        TimeSlotRepository.generateTimeSlotDB(tempTimeSlots);
    }

    public static void deleteEntry(int id) throws IOException {
        Set<TimeSlot> tempSlots = TimeSlotRepository.readTimeSlotDB();
        Set<TimeSlot> newTimeSlotDB = new LinkedHashSet<>();
        for (TimeSlot slot : tempSlots) {
            if (slot.getId() != id) {
                newTimeSlotDB.add(slot);
            }
        }
        TimeSlotRepository.generateTimeSlotDB(newTimeSlotDB);
    }
}
