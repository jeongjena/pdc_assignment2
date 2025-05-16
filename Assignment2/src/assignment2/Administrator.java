/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import static assignment2.Room.RoomType.DOUBLE;
import static assignment2.Room.RoomType.SINGLE;
import static assignment2.Room.RoomType.SUITE;
import java.util.Map;

/**
 *
 * @author uj265
 */

/*
 * Administrator class provides admin-only functionalities:
 * - Verifies admin access via password
 * - Adds new rooms with validation
 * - Removes rooms if no active bookings exist
 *
 * This class interacts with UserPrompt for input and FileManager for persistence.
 */
public class Administrator {

    private static final String ADMIN_PASSWORD = "admin123";
    
    public Administrator() {
    }

    /**
     * Verifies admin credentials using promptString()
     * @return true if password is correct, false if user cancels
     */
    public static boolean checkAdminPassword() {
        while (true) {
            String input = UserPrompt.promptString("\nEnter administrator password");
            if (input == null) {
                return false;
            }
            if (input.equals(ADMIN_PASSWORD)) {
                return true;
            } else {
                System.out.println("Incorrect password. Try again.");
            }
        }
    }

    /**
     * Adds a new room by:
     * - Prompting for room number and checking duplication
     * - Getting and validating room type
     * - Collecting rates and guest limit
     * - Writing the updated room list to file
     */
    public static void addRoom() {
        Map<Integer, Room> rooms = FileManager.readRooms();
        int roomNumber;

        // Prompt for unique room number
        while (true) {
            roomNumber = UserPrompt.promptInt("\nEnter room number to add");
            if (roomNumber == -1) {
                return;
            }
            if (rooms.containsKey(roomNumber)) {
                System.out.println("Room number already exists. Try a different number.");
            } else {
                break;
            }
        }

        // Prompt for valid room type (SINGLE, DOUBLE, SUITE)
        Room.RoomType roomType = null;
        while (roomType == null) {
            String input = UserPrompt.promptString("Enter room type (SINGLE/DOUBLE/SUITE)");
            if (input == null) {
                return;
            }
            try {
                roomType = Room.RoomType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid room type.");
            }
        }

        // Get pricing info
        double baseRate = UserPrompt.promptDouble("Enter base rate");
        if (baseRate == -1.00) {
            return;
        }
        double ratePerPerson = UserPrompt.promptDouble("Enter rate per person");
        if (ratePerPerson == -1.00) {
            return;
        }
        
        // Create specific room object
        switch (roomType) {
            case SINGLE:
                rooms.put(roomNumber, new SingleRoom(roomNumber, baseRate, ratePerPerson));
                break;
            case DOUBLE:
                rooms.put(roomNumber, new DoubleRoom(roomNumber, baseRate, ratePerPerson));
                break;
            case SUITE:
                int maxGuests = UserPrompt.promptInt("Enter maximum guests capacity for suite");
                if (maxGuests == -1) {
                    return;
                }
                rooms.put(roomNumber, new SuiteRoom(roomNumber, baseRate, ratePerPerson, maxGuests));
                break;
            default:
                System.out.println("Invalid room type. Room not added.");
                return;
        }
        FileManager.writeRooms(rooms); // Persist updated room list
        System.out.println("Room added successfully.");
    }

    /**
     * Removes a room if:
     * - It exists
     * - No current bookings are associated with it
     * 
     * Otherwise, informs the user accordingly.
     */
    public static void removeRoom() {
        Map<Integer, Room> rooms = FileManager.readRooms();

        while (true) {
            int roomNumber = UserPrompt.promptInt("\nEnter room number to remove");
            if (roomNumber == -1) {
                return;
            }
            Room targetRoom = rooms.get(roomNumber);
            if (targetRoom != null) {
                Map<Integer, Booking> bookings = FileManager.readBookings();
                boolean bookingExists = false;
                // Check for conflicting bookings
                for (Booking b : bookings.values()) {
                    if (b.getRoomNumber() == roomNumber) {
                        bookingExists = true;
                        break;
                    }
                }
                if (bookingExists) {
                    System.out.println("Cannot remove room. Booking exists for this room.");
                } else {
                    rooms.remove(roomNumber);
                    FileManager.writeRooms(rooms);
                    System.out.println("Room number " + roomNumber + " removed successfully.");
                    return;
                }
            } else {
                System.out.println("Room number " + roomNumber + " not found.");
            }
        }
    }
}
