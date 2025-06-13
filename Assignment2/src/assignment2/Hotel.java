/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author uj265
 */

/*
 * The Hotel class acts as the core controller for the booking system.
 * It manages collections of Room and Booking objects using HashMaps,
 * and communicates with FileManager to persist data to and from text files.
 * It applies object-oriented principles such as encapsulation and delegation.
 */
public class Hotel {

    // Room and Booking data are stored using HashMaps for efficient lookups
    private Map<Integer, Room> rooms;
    private Map<Integer, Booking> bookings;

    // Constructor initializes empty maps
    public Hotel() {
        rooms = new HashMap<>();
        bookings = new HashMap<>();
    }

    // Reads room data from file and loads it into the rooms map
    public void readRooms() {
        rooms.clear();
        rooms = RoomDAO.readRooms();
    }

    // Reads booking data from file and loads it into the bookings map
    public void readBookings() {
        bookings.clear();
        bookings = BookingDAO.readBookings();
    }

    // Allows user to select a room by entering a room number
    public int selectRoom() {
        while (true) {
            int roomNumber = UserPromptView.promptInt("Please enter the room number you would like to book");
            if (roomNumber == -1) {
                return -1;
            }
            if (rooms.containsKey(roomNumber)) {
                return roomNumber;
            }
            UserPromptView.showMessage("Room not found.");
        }
    }

    // Removes rooms from the list if they can't accommodate the given number of guests
    public void removeRoomByGuestNumber(int guestNumber) {
        Iterator<Map.Entry<Integer, Room>> iterator = rooms.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Room> entry = iterator.next();
            if (entry.getValue().getMaxGuests() < guestNumber) {
                iterator.remove();
            }
        }
    }

    // Removes rooms that are already booked between check-in and check-out dates
    public void removeRoomByDate(LocalDate checkInDate, LocalDate checkOutDate) {
        Iterator<Map.Entry<Integer, Room>> iterator = rooms.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Room> entry = iterator.next();
            for (Booking b : bookings.values()) {
                if (entry.getKey() == b.getRoomNumber() && checkOutDate.isAfter(b.getCheckInDate()) && checkInDate.isBefore(b.getCheckOutDate())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    // Prints a list of all available rooms
    public void printRooms() {
        if (rooms.isEmpty()) {
            UserPromptView.showError("No rooms available.");
            return;
        }
        var roomList = "List of rooms: \n";
        for (Room r : rooms.values()) {
            roomList += r.printRoom();
        }
        UserPromptView.showMessage(roomList);
    }

    // Prints rooms with total price calculation for the given number of nights and guests
    public void printRoomsWithPrice(int nights, int numGuests) {
        if (rooms.isEmpty()) {
            UserPromptView.showError("No rooms available");
            return;
        }
        for (Room r : rooms.values()) {
            r.printRoomWithPrice(numGuests, nights);
        }
    }

    // Finds the next booking number by checking the current max in the map keys
    public int findNextBookingNumber() {
        int max = 0;
        for (int b : bookings.keySet()) {
            if (b > max) {
                max = b;
            }
        }
        return max + 1;
    }

    // Displays booking details and confirms via password
    public boolean doubleCheckBooking(Booking booking) {
        Room r = rooms.get(booking.getRoomNumber());
        int nights = (int) ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        booking.printBookingWithPrice(r, nights);

        return booking.getGuest().checkPassword("Enter the 4 digit password to confirm the booking");
    }

    // Adds a new booking and writes updated data to the file
    public void makeBooking(Booking newBooking) {
        int bookingNumber = findNextBookingNumber();
        newBooking.setBookingNumber(bookingNumber);
        bookings.put(bookingNumber, newBooking);
        BookingDAO.addBooking(newBooking);
        UserPromptView.showMessage("Booking Number: " + bookingNumber + " is successfully booked");
    }

    public void findAvailableRooms(Booking booking) {
        readRooms();
        readBookings();
        removeRoomByGuestNumber(booking.getNumberOfGuests()); // Filter rooms by guest number
        removeRoomByDate(booking.getCheckInDate(), booking.getCheckOutDate()); // Filter rooms by availability
    }

    // Cancels a booking if the correct password is provided
    public boolean cancelBooking(int bookingNumber) {
        Booking targetBooking = bookings.get(bookingNumber);

        if (targetBooking == null) {
            UserPromptView.showError("Booking not found.");
            return false;
        } else {
            if (targetBooking.getGuest().checkPassword("Enter your 4 digit password to cancel the booking")) {
                bookings.remove(bookingNumber);
            } else {
                return false;
            }
            if (BookingDAO.removeBooking(bookingNumber)) {
                UserPromptView.showMessage("Booking Number: " + bookingNumber + " is successfully cancelled");
            } else {
                UserPromptView.showError("Cancellation failed.");
            }
        }
        return true;
    }

    /**
     * @return the rooms
     */
    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    /**
     * @return the bookings
     */
    public Map<Integer, Booking> getBookings() {
        return bookings;
    }
}
