/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author uj265
 */

/*
 * FileManager handles all file I/O operations for the Hotel Booking System.
 * - Reading and writing room and booking data from/to text files
 * - Ensuring file paths and directories are available
 * - Providing reusable, modular methods to support persistence
 */
public class FileManager {

    private static final String BASE_PATH = "./resources";
    private static final String ROOMS_PATH = "./resources/ROOMS.txt";
    private static final String BOOKINGS_PATH = "./resources/BOOKINGS.txt";

    /**
     * Reads all room numbers from ROOMS.txt to prevent duplication
     * @return Set of existing room numbers
     */
    public static Set<Integer> readRoomNumbers() {
        BufferedReader reader = null;
        Set<Integer> roomNumbers = new HashSet<>();
        checkPath(ROOMS_PATH);
        try {
            reader = new BufferedReader(new FileReader(ROOMS_PATH));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int roomNumber = Integer.parseInt(parts[0]);
                roomNumbers.add(roomNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return roomNumbers;
    }
    
    // Retrieves a single Room object based on the room number
    public static Room getRoom(int roomNumber) {
        BufferedReader reader = null;
        checkPath(ROOMS_PATH);
        try {
            reader = new BufferedReader(new FileReader(ROOMS_PATH));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[0]) == roomNumber) {
                    String type = parts[1];
                    switch (type) {
                        case "SINGLE":
                            return new SingleRoom(roomNumber, Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
                        case "DOUBLE":
                            return new DoubleRoom(roomNumber, Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
                        case "SUITE":
                            return new SuiteRoom(roomNumber, Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // Loads all rooms from the ROOMS.txt file
    public static Map<Integer, Room> readRooms() {
        BufferedReader reader = null;
        Map<Integer, Room> rooms = new HashMap<>();
        checkPath(ROOMS_PATH);
        try {
            reader = new BufferedReader(new FileReader(ROOMS_PATH));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int roomNumber = Integer.parseInt(parts[0]);
                String type = parts[1];
                switch (type) {
                    case "SINGLE":
                        rooms.put(roomNumber, new SingleRoom(roomNumber, Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                        break;
                    case "DOUBLE":
                        rooms.put(roomNumber, new DoubleRoom(roomNumber, Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                        break;
                    case "SUITE":
                        rooms.put(roomNumber, new SuiteRoom(roomNumber, Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4])));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return rooms;
    }

    // Saves all room data to ROOMS.txt
    public static boolean writeRooms(Map<Integer, Room> rooms) {
        PrintWriter writer = null;
        checkPath(ROOMS_PATH);
        try {
            writer = new PrintWriter(ROOMS_PATH);
            for (Room r : rooms.values()) {
                writer.println(r.getRoomNumber() + " " + r.getRoomType() + " " + r.getBaseRate() + " " + r.getRatePerPerson() + " " + r.getMaxGuests());
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Reads all booking numbers to validate existence
    public static Set<Integer> readBookingNumbers() {
        BufferedReader reader = null;
        Set<Integer> bookingNumbers = new HashSet<>();
        checkPath(BOOKINGS_PATH);
        try {
            reader = new BufferedReader(new FileReader(BOOKINGS_PATH));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int bookingNumber = Integer.parseInt(parts[0]);
                bookingNumbers.add(bookingNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return bookingNumbers;
    }
    
    // Retrieves a specific Booking object from file
    public static Booking getBooking(int bookingNumber) {
        BufferedReader reader = null;
        checkPath(BOOKINGS_PATH);
        try {
            reader = new BufferedReader(new FileReader(BOOKINGS_PATH));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[0]) == bookingNumber) {
                    return new Booking(Integer.parseInt(parts[0]), LocalDate.parse(parts[1]), LocalDate.parse(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), new Guest(parts[5], parts[6], parts[7], Integer.parseInt(parts[8])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // Loads all bookings from the BOOKINGS.txt file
    public static Map<Integer, Booking> readBookings() {
        BufferedReader reader = null;
        Map<Integer, Booking> bookings = new HashMap<>();
        checkPath(BOOKINGS_PATH);
        try {
            reader = new BufferedReader(new FileReader(BOOKINGS_PATH));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int bookingNumber = Integer.parseInt(parts[0]);
                bookings.put(bookingNumber, new Booking(Integer.parseInt(parts[0]), LocalDate.parse(parts[1]), LocalDate.parse(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), new Guest(parts[5], parts[6], parts[7], Integer.parseInt(parts[8]))));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return bookings;
    }
    
    // Writes all bookings back to BOOKINGS.txt
    public static boolean writeBookings(Map<Integer, Booking> bookings) {
        PrintWriter writer = null;
        checkPath(BOOKINGS_PATH);
        try {
            writer = new PrintWriter(BOOKINGS_PATH);
            for (Booking b : bookings.values()) {
                writer.println(b.getBookingNumber() + " " + b.getCheckInDate() + " " + b.getCheckOutDate() + " " + b.getRoomNumber() + " " + b.getNumberOfGuests() + " " + b.getGuest().getFirstName() + " " + b.getGuest().getLastName() + " " + b.getGuest().getPhone() + " " + b.getGuest().getPassword());
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Ensures file and directory exist before any I/O operation
    private static void checkPath(String filePath) {
        try { // Code assisted by ChatGPT.
            File file = new File(BASE_PATH);
            File txtFile = new File(filePath);

            // Create file if it doesn't exist
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!txtFile.exists()) {
                txtFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + filePath);
        }
    }
}
