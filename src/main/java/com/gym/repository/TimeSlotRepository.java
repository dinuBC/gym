package com.gym.repository;

import com.gym.model.TimeSlot;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashSet;
import java.util.Set;

public class TimeSlotRepository {
    private static final String[] HEADERS = {"Identifier", "Trainer_ID", "Start_Time", "No_of_Subscribers", "Clients"};
    private static final String timeSlotDBLocation = "src/main/resources/timeSlotDB.csv";

    public static void generateTimeSlotDB(Set<TimeSlot> timeSlots) throws IOException {
        FileWriter out = new FileWriter(timeSlotDBLocation);
        CSVPrinter printer = CSVFormat.DEFAULT.withHeader(HEADERS).print(out);
        for (TimeSlot slot : timeSlots) {
            printer.printRecord(slot.getId(), slot.getTrainer().getId(), slot.getStart().toString(), slot.getMaxSubscribers(), slot.getClientList());
        }
        printer.flush();
        printer.close();
        out.close();
    }

    public static int clientBookedSlots(int clientID) {
        int numberOfSlots = 0;

        return numberOfSlots;
    }

    public static Set<TimeSlot> readTimeSlotDB() throws IOException {
        Set<TimeSlot>dbTimeSlot= new LinkedHashSet<>();
        Reader in = new FileReader(timeSlotDBLocation);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record: records){
            String id = record.get("Identifier");
            String trainerID = record.get("Trainer_ID");
            String startTime = record.get("Start_Time");
            String subscriberNo = record.get("No_of_Subscribers");
        }
        return dbTimeSlot;
    }
}
