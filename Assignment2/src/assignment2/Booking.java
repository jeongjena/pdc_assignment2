/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 *
 * @author uj265
 */

/*
 * The Booking class represents a single booking made by a guest.
 * It stores details such as the booking number, check-in and check-out dates,
 * room number, number of guests, and associated Guest information.
 * It includes methods for printing booking details and validating bookings with password checks.
 */
public class Booking {

    private int bookingNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int roomNumber;
    private int numberOfGuests;
    private Guest guest;

    // Default constructor
    public Booking() {

    }

    // Constructor without booking number (used before assigning ID)
    public Booking(LocalDate checkInDate, LocalDate checkOutDate, int roomNumber, int numberOfGuests, Guest guest) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.numberOfGuests = numberOfGuests;
        this.guest = guest;
    }

    // Constructor with all fields (used when reading from file)
    public Booking(int bookingNumber, LocalDate checkInDate, LocalDate checkOutDate, int roomNumber, int numberOfGuests, Guest guest) {
        this.bookingNumber = bookingNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.numberOfGuests = numberOfGuests;
        this.guest = guest;
    }

    /**
     * Static method to display a booking based on its ID. It checks for the
     * existence of the booking and associated room, and requests password
     * authentication before printing.
     */
    public static boolean printBooking(int bookingNumber) {
        Booking b = BookingDAO.getBooking(bookingNumber);
        if (b == null) {
            UserPromptView.showError("Booking not found.");
            return false;
        }
        Room r = RoomDAO.getRoom(b.getRoomNumber());
        if (r == null) {
            UserPromptView.showError("Room not found for this booking.");
            return false;
        }
        
        if (b.getGuest().checkPassword("Enter your 4 digit password to view the booking")) {
            b.printBookingWithPrice(r, b.calculateNights());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Displays detailed booking information with price breakdown.
     *
     * @param room Room object used to calculate total price
     * @param nights Number of nights the room is booked for
     */
    public void printBookingWithPrice(Room room, int nights) {
        UserPromptView.showMessage("First Name: " + guest.getFirstName() + "\nLast Name: " + guest.getLastName() + "\nPhone Number: " + guest.getPhone()
                + "\nRoom Number: " + roomNumber + "\nCheck-in Date: " + checkInDate + "\nCheck-out Date: " + checkOutDate
                + "\nNumber Of Guests: " + numberOfGuests + "\nTotal Price: $" + room.calculatePrice(numberOfGuests) * nights);
    }

    /**
     * Prompts the user to enter a booking number and validates it against
     * existing records.
     *
     * @return the valid booking number or -1 if cancelled
     */
    public static int askBookingNumber() {
        Set<Integer> bookingNumbers = BookingDAO.readBookingNumbers();
        while (true) {
            int bookingNumber = UserPromptView.promptInt("\nEnter your booking number");
            if (bookingNumber == -1) {
                return -1;
            }
            if (bookingNumbers.contains(bookingNumber)) {
                return bookingNumber;
            }
            UserPromptView.showError("Booking not found.");
        }
    }

    public int askGuestNumber() {        // Booking process
        return UserPromptView.promptInt("\nPlease enter the number of guests");
    }

    public LocalDate askCheckInDate() {
        return UserPromptView.promptCheckInDate("Please enter your check-in date");
    }

    public LocalDate askCheckOutDate() {
        return UserPromptView.promptCheckOutDate(checkInDate, "Please enter your check-out date");
    }

    public int calculateNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }
    
    public Boolean askGuestInfo() {
        Guest guest = new Guest();
        if (!guest.getGuestInfo()) {
            return false;
        }
        if (guest.getGuestPassword() == -1) {
            return false;
        }
        this.guest = guest;
        return true;
    }

    // --- Getter and Setter methods ---
    public int getBookingNumber() {
        return bookingNumber;
    }

    /**
     * @param bookingNumber the bookingNumber to set
     */
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    /**
     * @return the checkInDate
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * @param checkInDate the checkInDate to set
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * @return the checkOutDate
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * @param checkOutDate the checkOutDate to set
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the numberOfGuests
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * @param numberOfGuests the numberOfGuests to set
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * @return the guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
