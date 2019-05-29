package com.gym.repository;

import com.gym.model.Location;
import com.gym.model.Room;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class RoomRepository {
    static String[] HEADERS = {"identifier", "capacity", "location"};
    static String roomDBLocation = "src/main/resources/roomDB.csv";

    public static void generateRoomDB(Set<Room> roomList) {
        try {
            FileWriter out = new FileWriter(roomDBLocation);
            CSVPrinter printer = CSVFormat.DEFAULT.withHeader("identifier", "capacity", "location").print(out);
            for (Room room : roomList) {
                printer.printRecord(room.getId(), room.getCapacity(), room.getLocation().getId());
            }
            printer.flush();
            printer.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Room> readRoomDB() throws IOException {
        Set<Room> dbRooms = new LinkedHashSet<>();
        Reader in = new FileReader(roomDBLocation);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String id = record.get("identifier");
            String capacity = record.get("capacity");
            String location = record.get("location");
            Room room = new Room();
            room.setId(Integer.parseInt(id));
            room.setCapacity(Integer.parseInt(capacity));
            Location dbLocation = new Location();
            dbLocation.setId(Integer.parseInt(location));
            room.setLocation(dbLocation);
            dbRooms.add(room);
        }
        return dbRooms;
    }

    public static void updateRoomDB(int id, Room updatedRoom) throws IOException {
        Set<Room> tempRooms = RoomRepository.readRoomDB();
        for (Room room : tempRooms) {
            if (room.getId() == id) {
                room.setLocation(updatedRoom.getLocation());
                room.setCapacity(updatedRoom.getCapacity());
                room.setId(updatedRoom.getId());
            }
        }
        RoomRepository.generateRoomDB(tempRooms);
    }

    public static void deleteEntry(int id) throws IOException {
        Set<Room> tempRooms = RoomRepository.readRoomDB();
        Set<Room> newRoomDB = new LinkedHashSet<>();
        for (Room room : tempRooms) {
            if (room.getId() != id) {
                newRoomDB.add(room);
            }
        }
        RoomRepository.generateRoomDB(newRoomDB);
    }
}

